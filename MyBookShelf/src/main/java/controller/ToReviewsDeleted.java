package controller;

import entity.ReviewList;
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
import java.util.List;

/**
 * Created by Lora on 5/10/16.
 *
 * Takes the review ids from the deleteReviews page and removes them from the database, then returns you the deleted
 * reviews page with the review removed.
 */
@WebServlet(
        name = "goToReviewsDeleted",
        urlPatterns = { "/goto-reviews-deleted" }
)
public class ToReviewsDeleted extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        ReviewListDaoWithHibernate allReviews = new ReviewListDaoWithHibernate();
        List<ReviewList> reviewsToReview = new ArrayList<ReviewList>();
        reviewsToReview = allReviews.getAllReviews();
        String deleteId = "";

                ReviewList review = new ReviewList();

        //go through the reviews
        for(ReviewList aReview : reviewsToReview) {
            int reviewId = (Integer) aReview.getReview_id();
            String reviewIdString = Integer.toString(reviewId);
            deleteId = request.getParameter(reviewIdString);

            log.info("reviewIdString: " + reviewIdString);
            log.info("deleteId: " + deleteId);

            //check if any of the reviews were deleted
            if(reviewIdString.equals(deleteId)){

                log.info("in if deleteId = reviewIdString");

                review.setBook_id(aReview.getBook_id());
                review.setNotes(aReview.getNotes());
                review.setRating(aReview.getRating());
                review.setReading_id(aReview.getReading_id());
                review.setReview_id(aReview.getReview_id());
                review.setUser_id(aReview.getUser_id());

                allReviews.deleteReview(review);
            }
        }

        String urlForward = "/goto-delete-reviews";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
