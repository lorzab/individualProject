package persistance;

import entity.ReviewList;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 5/1/16.
 */
public class ReviewListDaoWithHibernate implements ReviewListDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Get all of the reviews from the database
     * @return all of the reviews from the database
     */
    @Override
    public List<ReviewList> getAllReviews() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<ReviewList> allReviews = new ArrayList<ReviewList>();

        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(ReviewList.class);
            List<ReviewList> reviewLists = criteria.list();

            for (ReviewList r: reviewLists) {
                allReviews.add(r);
            }

            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                log.error(e);
            }
        } finally {
            session.close();
        }
        return allReviews;
    }

    /**
     * Update a review in the database
     * @param review item to be updated
     */
    @Override
    public void updateReview(ReviewList review) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(review);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error(e);
        } finally {
            session.close();
        }
    }

    /**
     * delete a review from the database
     * @param review item to be deleted
     */
    @Override
    public void deleteReview(ReviewList review) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(review);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error(e);
        } finally {
            session.close();
        }
    }

    /**
     * Add a review to the database
     * @param review item to be added
     * @return reviewId
     */
    @Override
    public int addReview(ReviewList review) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = 0;
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(review);
            tx.commit();
            log.info("Added book: " + review + " with isbn of: " + review.getReview_id());
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * Get the recomendation % of a book if there are any recommendations for it
     * @param book_id book to get the percentage from
     * @return recommendation percentage from readers
     */
    public double calcuateRecommendationPercentage(int book_id) {
        List<ReviewList> allReviews = new ArrayList<ReviewList>();
        allReviews = getAllReviews();

        int count = 0;
        int rating = 0;
        double recommendatedPercentage = 0;

        for (ReviewList review : allReviews) {

            if(review.getBook_id() == book_id) {
                count++;
                rating += review.getRating();
            }
        }

        log.info("count: " + count);
        log.info("rating: " + rating);

        if (count != 0) {
            recommendatedPercentage = (rating / count) * 100;

        }

        log.info("recommendationPercentage: " + recommendatedPercentage);

        return recommendatedPercentage;

    }

    /**
     * Get notes from bookId, userId, readingId
     * @param bookId the book to be searched for
     * @param userId the user that reviewed the book
     * @param readingId the id of when the book was added on a reading list
     * @return the notes that were given for the review
     */
    public String getNotesFromReview(int bookId, int userId, int readingId){
        List<ReviewList> allReviews = new ArrayList<ReviewList>();
        allReviews = getAllReviews();
        String notes = "";

        //log.info("in review");

        for(ReviewList review : allReviews) {
            //log.info(review.getBook_id());
            //log.info(bookId);

            if(review.getBook_id() == bookId) {
                log.info("in book check");
                if(review.getUser_id() == userId) {
                    log.info("in user check");
                    if(review.getReading_id() == readingId) {
                        notes = review.getNotes();
                        log.info("notes: " + notes);
                    }
                }
            }
        }
        return notes;
    }

    /**
     * Get rating of book from userId, bookId, readingId
     * @param bookId the book to be searched for
     * @param userId the user who reviewed the book
     * @param readingId the readinglist that the book is on
     * @return the rating that the user gave the book
     */
    public double getRatingFromReview(int bookId, int userId, int readingId) {
        List<ReviewList> allReviews = new ArrayList<ReviewList>();
        allReviews = getAllReviews();
        double rating = 0;

        for(ReviewList review : allReviews) {
            if(review.getBook_id() == bookId) {
                if(review.getUser_id() == userId) {
                    if(review.getReading_id() == readingId) {
                        rating = review.getRating();
                    }
                }
            }
        }
        return rating;
    }

    /**
     * Get reviewId from userId, bookId, readingId
     * @param bookId the book to be searched for
     * @param userId the user that gave the review
     * @param readingId the reading list that it is from
     * @return the review id for the review written
     */
    public int getReviewIdFromForeignKeys(int bookId, int userId, int readingId) {
        List<ReviewList> allReviews = new ArrayList<ReviewList>();
        allReviews = getAllReviews();
        int reviewId = 0;

        for(ReviewList review : allReviews) {
            if(review.getBook_id() == bookId) {
                if(review.getUser_id() == userId) {
                    if(review.getReading_id() == readingId) {
                        reviewId = review.getReview_id();
                    }
                }
            }
        }
        return reviewId;
    }

    /**
     * Get the list of books what have a recommended % better than 50%
     * @param userId the user that needs recommendations
     * @return the list of books over the percentage that the user has not read
     */
    public ArrayList<ArrayList<String>> getRecommendedBooksUserHasNotRead(int userId) {
        List<ReviewList> allReviews = new ArrayList<ReviewList>();
        allReviews = getAllReviews();
        BookDaoWithHibernate book = new BookDaoWithHibernate();
        ArrayList<String> bookInfo = null;
        ArrayList<ArrayList<String>> allRecommendatedBooks = new ArrayList<ArrayList<String>>();
        double recommendationPercentage = .5;


        for(ReviewList review : allReviews) {
            //check to make sure the user did write the review
            if(review.getUser_id() != userId) {
                bookInfo = new ArrayList<String>();
                double recommendatingRating = calcuateRecommendationPercentage(review.getBook_id());

                if(recommendatingRating > recommendationPercentage) {
                    //return array of title, author, note, recommendationPercentage,
                    int bookId = review.getBook_id();
                    String bookIdString = Integer.toString(bookId);
                    String title = book.getTitleFromId(bookId);
                    String author = book.getAuthorFromId(bookId);
                    String note = review.getNotes();
                    String recommendedString = Double.toString(recommendatingRating);

                    bookInfo.add(bookIdString);
                    bookInfo.add(title);
                    bookInfo.add(author);
                    bookInfo.add(note);
                    bookInfo.add(recommendedString);

                    allRecommendatedBooks.add(bookInfo);
                }
            }
        }
        return allRecommendatedBooks;
    }

    /**
     * Get reviews to look over
     * @return the reviews to be looked over
     */
    public ArrayList<ArrayList<String>> getReviewsToModerate() {
        List<ReviewList> allReviews = new ArrayList<ReviewList>();
        allReviews = getAllReviews();

        ArrayList<String> reviewInfo = null;
        ArrayList<ArrayList<String>> reviewsToReview = new ArrayList<ArrayList<String>>();

        for (ReviewList review : allReviews) {
            reviewInfo = new ArrayList<String>();

            reviewInfo.add(Integer.toString(review.getReview_id()));
            reviewInfo.add(review.getNotes());

            log.info("review id: " + review.getReview_id());

            reviewsToReview.add(reviewInfo);
        }
        return reviewsToReview;
    }
}
