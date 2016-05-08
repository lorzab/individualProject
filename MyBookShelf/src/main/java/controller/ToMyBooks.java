package controller;

import entity.UserReadingList;
import org.apache.log4j.Logger;

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
 */
@WebServlet(
        name = "goToMyBooks",
        urlPatterns = { "/goto-my-books" }
)
public class ToMyBooks extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        //get user from session
        int userId = (Integer) session.getAttribute("userId");

        //get all books on my reading list, not including wishlist
        UserReadingList allReadingList = new UserReadingList();


        String urlForward = "/jsp/myBooks.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
