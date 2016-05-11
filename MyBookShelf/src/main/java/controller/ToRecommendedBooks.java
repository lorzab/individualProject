package controller;

import org.apache.log4j.Logger;
import persistance.ReviewListDaoWithHibernate;
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
 *
 * This gets all of the books that are recommened over a certain percentage and displays them on screen
 */
@WebServlet(
        name = "goToRecommendedBooks",
        urlPatterns = { "/goto-recommended-books" }
)
public class ToRecommendedBooks extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        int userId = (Integer) session.getAttribute("userId");

        ReviewListDaoWithHibernate reviewList = new ReviewListDaoWithHibernate();
        ArrayList<ArrayList<String>> recommendedBooks = new ArrayList<ArrayList<String>>();
        recommendedBooks = reviewList.getRecommendedBooksUserHasNotRead(userId);
        session.setAttribute("recommendedBooks", recommendedBooks);

        String urlForward = "/jsp/recommendedBooks.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
