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
 * Created by Lora on 5/10/16.
 *
 * This servlet pulls up all of the reviews to be looked over and see if any need to be deleted
 */
@WebServlet(
        name = "goToDeleteReviews",
        urlPatterns = { "warArchive/goto-delete-reviews" }
)
public class ToDeleteReviews extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        ArrayList<ArrayList<String>> reviewsToReview = new ArrayList<ArrayList<String>>();
        ReviewListDaoWithHibernate allReviews = new ReviewListDaoWithHibernate();
        reviewsToReview = allReviews.getReviewsToModerate();

        session.setAttribute("reviewToModerate", reviewsToReview);

        String urlForward = "warArchive/jsp/deleteReviews.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
