package persistance;

import entity.User;
import entity.UserReadingList;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Lora on 4/24/16.
 */
public class UserReadingListDaoWithHibernate implements UserReadingListDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Get all of the user reading list from the database
     * @return all of the user reading lsit
     */
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

    /**
     * Update a user reading list
     * @param userList the user reading list to be updated
     */
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

    /**
     * Delete a user readinglist
     * @param userList the list to be deleted
     */
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

    /**
     * Add a new user reading list
      * @param userList the list to be added
     * @return the reading list id
     */
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
     * @param userId the user that has the book on their reading lsit
     * @param bookId the book being searched for
     * @return the id of the reading lsit
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
     * @param userId the user that is being searched for
     * @param wantToSeeWishList do they want to see the books that have been read or the books that they want to read
     * @return the list of books requested
     */
    public ArrayList<ArrayList<String>> getUserReadingList(int userId, int wantToSeeWishList) {
        List<UserReadingList> allUsersReadingList = new ArrayList<UserReadingList>();
        allUsersReadingList = getAllUserReadingList();
        ArrayList<ArrayList<String>> userReadingList = new ArrayList<ArrayList<String>>();
        ArrayList<String> bookInfo = null;

        BookDaoWithHibernate book = new BookDaoWithHibernate();
        ReviewListDaoWithHibernate review = new ReviewListDaoWithHibernate();

        for(UserReadingList bookToRead : allUsersReadingList) {
            if(bookToRead.getUser_id() == userId) {
                int wishList = bookToRead.getWish_list();

                //if book is on the wish list dont show
                if (wishList == wantToSeeWishList) {
                    bookInfo = new ArrayList<String>();

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
        log.info("user reading list size" + userReadingList.size());
        return userReadingList;
    }

    /**
     * check to see if a person has read a book
     * @param userId user to see if they have read a book
     * @param bookId book being checked on
     * @return if the book has been read or not
     */
    public boolean hasUserReadBook(int userId, int bookId) {
        List<UserReadingList> allUsersReadingList = new ArrayList<UserReadingList>();
        allUsersReadingList = getAllUserReadingList();
        boolean hasReadBook = false;

        for(UserReadingList readingList : allUsersReadingList) {
            if(readingList.getUser_id() == userId) {
                if(readingList.getBook_id() == bookId) {
                    if(readingList.getWish_list() == 0) {
                        hasReadBook = true;
                    }
                }
            }
        }
        return hasReadBook;
    }

    /**
     * Get the current date
     * @return the current date to be entered to the database
     */
    public String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(date);
        log.info(date1);

        return date1;
    }


}
