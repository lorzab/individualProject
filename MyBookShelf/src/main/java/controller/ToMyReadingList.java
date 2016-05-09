package controller;

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
import java.util.ArrayList;

/**
 * Created by Lora on 5/8/16.
 */
@WebServlet(
        name = "goToMyReadingList",
        urlPatterns = { "/goto-my-reading-list" }
)
public class ToMyReadingList extends HttpServlet{

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        //get user from session
        int userId = (Integer) session.getAttribute("userId");

        //get all books on my reading list, not including wishlist
        UserReadingListDaoWithHibernate allReadingList = new UserReadingListDaoWithHibernate();
        ArrayList<ArrayList<String>> myBooks = new ArrayList<ArrayList<String>>();
        myBooks = allReadingList.getUserReadingList(userId, 0);

        session.setAttribute("myBooks", myBooks);

        String urlForward = "/jsp/myReadingList.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
