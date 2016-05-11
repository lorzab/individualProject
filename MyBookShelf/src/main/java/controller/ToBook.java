package controller;

import entity.Book;
import entity.UserReadingList;
import org.apache.log4j.Logger;
import persistance.BookDaoWithHibernate;
import persistance.ReviewListDaoWithHibernate;
import persistance.UserReadingListDaoWithHibernate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 5/7/16.
 *
 * This servlet gets the information about the book that was clicked on to see more about it
 *
 */
@WebServlet(
        name = "goToBook",
        urlPatterns = { "/goto-book" }
)
public class ToBook extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        String urlForward = "/jsp/book.jsp";

        //get the book wanted
        BookDaoWithHibernate book = new BookDaoWithHibernate();
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = book.getAllBooks();
        String bookID = request.getParameter("bookID");
        log.info("bookId from page:" + bookID);
        String author = null;
        String isbn = null;
        String title = null;
        int id = 0;

        //look through books to find the book wanted and set info
        for (Book aBook : allBooks) {
            String idString = Integer.toString(aBook.getId());

            if(bookID.equals(idString)) {
                author = aBook.getAuthor();
                isbn = aBook.getIsbn();
                title = aBook.getTitle();
                id= aBook.getId();
            }
        }

        //get the recommendation rating if one
        ReviewListDaoWithHibernate review = new ReviewListDaoWithHibernate();
        double recomendationPercentage = review.calcuateRecommendationPercentage(id);

        //check if the book is on the user reading list
        int userId = 0;
        if((Integer) session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        //get readingId for book
        UserReadingListDaoWithHibernate wishList = new UserReadingListDaoWithHibernate();
        int readingId = wishList.getReadingIdFromUserAndBook(userId, id);
        log.info("readingid: " + readingId);
        log.info("userid: " + userId);

        int onWishList = 0;
        boolean wantToRead = false;

        if(readingId != 0) {
            List<UserReadingList> allUsersReadingList = new ArrayList<UserReadingList>();
            allUsersReadingList = wishList.getAllUserReadingList();

            for(UserReadingList readingList : allUsersReadingList) {
                if(readingList.getReading_id() == readingId) {
                    onWishList = readingList.getWish_list();
                    break;
                }
            }

            if(onWishList != 0) {
                wantToRead = true;
            }
        }

        log.info("bookId: " + id);
        log.info("wantToRead " + wantToRead);

        //has the user read the book
        UserReadingListDaoWithHibernate readingList = new UserReadingListDaoWithHibernate();
        boolean hasReadBook = readingList.hasUserReadBook(userId, id);

        session.setAttribute("book", title);
        session.setAttribute("author", author);
        session.setAttribute("isbn", isbn);
        session.setAttribute("recommencationPercentage", recomendationPercentage);
        session.setAttribute("bookId", id);
        session.setAttribute("onWishList", wantToRead);
        session.setAttribute("readingId", readingId);
        session.setAttribute("hasReadBook", hasReadBook);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
