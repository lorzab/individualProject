package controller;

import entity.Book;
import entity.ReviewList;
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

/**
 * Created by Lora on 5/8/16.
 *
 * After all the information about a book to be added is entered this takes the information and adds it
 * to the database and transfers to a confirmation page
 */
@WebServlet(
        name = "goToBookAdded",
        urlPatterns = { "/goto-my-book-added" }
)
public class ToBookAdded extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        int userId = (Integer) session.getAttribute("userId");

        //get input from form
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        String recommended = request.getParameter("rating");
        String notes = request.getParameter("notes");
        int rating = 0;
        //change the string to int for database
        if (recommended == null) {
            rating = 0;
        } else if (recommended.equals("Yes")){
            rating = 1;
        }

        //add book to database
        BookDaoWithHibernate bookShelf = new BookDaoWithHibernate();
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);
        int bookId = bookShelf.addBook(book);

        //add to userReadingList
        UserReadingListDaoWithHibernate thisUserReadingList = new UserReadingListDaoWithHibernate();
        UserReadingList readingList = new UserReadingList();
        readingList.setUser_id(userId);
        readingList.setBook_id(bookId);
        readingList.setWish_list(0);
        readingList.setDate_added(thisUserReadingList.getCurrentDate());
        int readingId = thisUserReadingList.addUserReadingList(readingList);

        //add to reviewList
        ReviewListDaoWithHibernate reviewList = new ReviewListDaoWithHibernate();
        ReviewList review = new ReviewList();
        review.setReading_id(readingId);
        review.setUser_id(userId);
        review.setRating(rating);
        review.setBook_id(bookId);
        review.setNotes(notes);
        int reviewId = reviewList.addReview(review);

        double recomendationPercentage = reviewList.calcuateRecommendationPercentage(bookId);

        session.setAttribute("book", title);
        session.setAttribute("isbn", isbn);
        session.setAttribute("author", author);
        session.setAttribute("recommencationPercentage", recomendationPercentage);
        session.setAttribute("bookId", bookId);
        session.setAttribute("onWishList", "false");
        session.setAttribute("readingId", readingId);
        session.setAttribute("hasReadBook", "true");

        String urlForward = "/jsp/bookAdded.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
