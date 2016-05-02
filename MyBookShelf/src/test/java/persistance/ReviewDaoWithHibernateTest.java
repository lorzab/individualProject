package persistance;

import entity.ReviewList;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lora on 5/1/16.
 */
public class ReviewDaoWithHibernateTest {

    @Before
    public void setup() {

        ReviewListDaoWithHibernate dao = new ReviewListDaoWithHibernate();
        int insertReviewId = 0;

        //create book for test
        ReviewList getAllReviews = new ReviewList();
        getAllReviews.setReview_id(0);
        getAllReviews.setBook_id(3);
        getAllReviews.setNotes("This is the best book ever");
        getAllReviews.setRating(5.0);
        getAllReviews.setReading_id(3);
        getAllReviews.setUser_id(3);
        insertReviewId = dao.addReview(getAllReviews);
    }

    @Test
    public void testGetAllReviews() throws Exception {

        ReviewListDaoWithHibernate dao = new ReviewListDaoWithHibernate();
        List<ReviewList> reviews = dao.getAllReviews();

        assertTrue("There is the wrong amount in the list", reviews.size() > 0);
    }

    @Test
    public void testUpdateReview() throws Exception {

        ReviewListDaoWithHibernate dao = new ReviewListDaoWithHibernate();
        ReviewList review = new ReviewList(2, 3, 3, 3, "This book Sucks", 0.0);

        dao.updateReview(review);
        assertEquals("This is the wrong review", "This book Sucks", review.getNotes());
    }

//    @Test
//    public void testDeleteReview() throws Exception {
//
//        ReviewListDaoWithHibernate dao = new ReviewListDaoWithHibernate();
//        ReviewList review = new ReviewList();
//        int sizeBefore;
//        int sizeAfter;
//        review.setReview_id(1);
//        sizeBefore = dao.getAllReviews().size();
//        dao.deleteReview(review);
//        sizeAfter = dao.getAllReviews().size();
//
//        assertTrue("The review was not deleted", sizeBefore > sizeAfter);
//    }

    @Test
    public void testAddReview() throws Exception {

        ReviewListDaoWithHibernate dao = new ReviewListDaoWithHibernate();
        int insertReviewId = 0;

        //create review to add
        ReviewList review = new ReviewList();
        review.setReading_id(99);
        review.setUser_id(99);
        review.setRating(0);
        review.setBook_id(99);
        review.setNotes("Awesome Sauce");

        insertReviewId = dao.addReview(review);

        assertTrue("Insert failed", insertReviewId > 0);
    }
}
