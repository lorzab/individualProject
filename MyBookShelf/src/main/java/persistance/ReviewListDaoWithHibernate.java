package persistance;

import entity.Book;
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
     * Get the recomendation % of a book if there is one
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
            recommendatedPercentage = (rating / count);

        }

        log.info("recommendationPercentage: " + recommendatedPercentage);

        return recommendatedPercentage;

    }
}
