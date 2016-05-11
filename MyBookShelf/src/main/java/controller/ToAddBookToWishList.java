package controller;

import entity.UserReadingList;
import org.apache.log4j.Logger;
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
 * This servlet takes a book that you want to add to your wishlist and puts it on the wishlist takes you to
 * a confirmation screen that it was added
 */
@WebServlet(
        name = "goToAddBookWishList",
        urlPatterns = { "/goto-book-changed-wishlist" }
)
public class ToAddBookToWishList extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        String urlForward = "/jsp/bookAddedToWishList.jsp";

        //get the readinglist change
        String wishlist = request.getParameter("wishList");
        session.setAttribute("onWishList", wishlist);
        log.info(wishlist);

        int wantToRead = 0;
        if (wishlist.equals("yes")) {
            wantToRead = 1;
        }

        log.info("want to read: " + wantToRead);

        //get the book information to change the book to on the wishlist in the database
        int readingId = (Integer) session.getAttribute("readingId");
        int userId = (Integer) session.getAttribute("userId");
        int bookId = (Integer) session.getAttribute("bookId");
        UserReadingList readingList = new UserReadingList();
        UserReadingListDaoWithHibernate allReadingList = new UserReadingListDaoWithHibernate();

        //if readingId = 0 add to userReadingList
        if(readingId == 0) {
            //create readinglist to add
            readingList.setUser_id(userId);
            readingList.setBook_id(bookId);
            readingList.setWish_list(wantToRead);
            readingList.setDate_added(allReadingList.getCurrentDate());

            allReadingList.addUserReadingList(readingList);

        } //if readingId != 0 update userReadingList
        else {
            readingList = new UserReadingList(readingId, userId, bookId, wantToRead, allReadingList.getCurrentDate());
            allReadingList.updateUserReadingList(readingList);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
