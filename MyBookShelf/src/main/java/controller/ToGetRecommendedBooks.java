package controller;

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
 *
 * Takes you to a page where you can get recommended books that have a certain percentage of recommendations
 */
@WebServlet(
        name = "goToGetRecommendedBooks",
        urlPatterns = { "/warArchive/goto-get-recommended-books" }
)
public class ToGetRecommendedBooks extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        String urlForward = "/warArchive/jsp/getRecommendedBooks.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
