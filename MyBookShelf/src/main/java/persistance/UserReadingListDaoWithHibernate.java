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
            Criteria criteria = session.createCriteria(User.class);
            List<UserReadingList> usersReadingList = criteria.list();

            for (UserReadingList u: usersReadingList) {
                allUsersReadingList.add(u);
            }

            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
}
