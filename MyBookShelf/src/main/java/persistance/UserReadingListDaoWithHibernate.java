package persistance;

import entity.User;
import entity.UserReadingList;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 4/24/16.
 */
public class UserReadingListDaoWithHibernate implements UserReadingListDao {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public List<UserReadingList> getAllUserReadingList() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserReadingList> allUsersReadingList = new ArrayList<UserReadingList>();

        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(UserReadingList.class);
            List<UserReadingList> readingLists = criteria.list();

            for (UserReadingList u: readingLists) {
                allUsersReadingList.add(u);
            }

            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                log.error(e);
            }
        } finally {
            session.close();
        }
        return allUsersReadingList;
    }

    @Override
    public void updateUserReadingList(UserReadingList userList) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(userList);
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
    public void deleteUserReadingList(UserReadingList userList) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(userList);
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
    public int addUserReadingList(UserReadingList userList) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = 0;

        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(userList);
            tx.commit();
            log.info("Added user: " + id);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
            return id;
        }

    }

    /**
     * Check to see if a specific book is on the user reading list
     */
    public int getReadingIdFromUserAndBook(int userId, int bookId) {

        List<UserReadingList> allUsersReadingList = new ArrayList<UserReadingList>();
        allUsersReadingList = getAllUserReadingList();
        int readingId = 0;

        for(UserReadingList bookToRead : allUsersReadingList) {
            //check to see if the user is logged in if not breah
            if(userId == 0) {
                break;
            } else {
                //see if the user has any books that they have added to wish list
                if(bookToRead.getUser_id() == userId) {
                    //if they have book on wish list see if it matches the book they want to see
                    if(bookToRead.getBook_id() == bookId) {
                        readingId = bookToRead.getBook_id();

                        break;
                    }
                }
            }

        }

        return readingId;
    }

    /**
     * Get books for user
     */
    public ArrayList<ArrayList<String>> getUserReadingList(int userId, int wantToSeeWishList) {
        List<UserReadingList> allUsersReadingList = new ArrayList<UserReadingList>();
        allUsersReadingList = getAllUserReadingList();
        ArrayList<ArrayList<String>> userReadingList = new ArrayList<ArrayList<String>>();
        ArrayList<String> bookInfo = new ArrayList<String>();

        BookDaoWithHibernate book = new BookDaoWithHibernate();
        ReviewListDaoWithHibernate review = new ReviewListDaoWithHibernate();

        for(UserReadingList bookToRead : allUsersReadingList) {
            if(bookToRead.getUser_id() == userId) {
                int wishList = bookToRead.getWish_list();

                //if book is on the wish list dont show
                if (wishList == wantToSeeWishList) {
                    log.info(bookToRead.getBook_id());
                    int bookId = bookToRead.getBook_id();

                    String dateAdded = bookToRead.getDate_added();
                    int readingId = bookToRead.getReading_id();

                    //get book title and author from bookID
                    String title = book.getTitleFromId(bookId);
                    String author = book.getAuthorFromId(bookId);
                    //get notes and recommended from bookId, userId and readingId
                    String notes = review.getNotesFromReview(bookId, userId, readingId);
                    int rating = (int) review.getRatingFromReview(bookId, userId, readingId);

                    String recommended = "Yes";
                    if (rating != 1) {
                        recommended = "No";
                    }

                    String bookIdString = Integer.toString(bookId);
                    //log.info(bookIdString);

                    bookInfo.add(bookIdString);
                    bookInfo.add(title);
                    bookInfo.add(author);
                    bookInfo.add(notes);
                    bookInfo.add(recommended);
                    bookInfo.add(dateAdded);
                    userReadingList.add(bookInfo);
                }
            }
        }
        log.info(userReadingList.size());
        return userReadingList;
    }


}
