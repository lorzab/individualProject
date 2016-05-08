package controller;

import org.apache.log4j.Logger;
import persistance.BookDaoWithHibernate;
import persistance.UserDaoWithHibernate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lora on 5/7/16.
 */
@WebServlet(
        name = "goToMyBookshelf",
        urlPatterns = { "/goto-my-bookshelf" }
)
public class ToMyBookshelf extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        //get they value of user name
        String userName = request.getRemoteUser();
        session.setAttribute("userName", userName);
        log.info(userName);

        //get the userId
        int userId = 0;
        UserDaoWithHibernate user = new UserDaoWithHibernate();
        userId = user.getUserIdFromUserName(userName);
        session.setAttribute("userId", userId);

        String urlForward = "/jsp/myBookshelf.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
        /*ServletContext context = getServletContext();
        response.sendRedirect(urlRedirect);*/
    }
}
