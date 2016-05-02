package persistance;

import entity.ReviewList;

import java.util.List;

/**
 * Created by Lora on 5/1/16.
 */
public interface ReviewListDao {

    public List<ReviewList> getAllReviews();
    public void updateReview(ReviewList review);
    public void deleteReview(ReviewList review);
    public int addReview(ReviewList review);
}
