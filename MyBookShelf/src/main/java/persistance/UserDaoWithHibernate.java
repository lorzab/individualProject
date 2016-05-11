package persistance;

import entity.Role;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 2/8/16.
 */
public class UserDaoWithHibernate implements UserDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Get all of the users from the database
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<User> allUsers = new ArrayList<User>();
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            List<User> users = criteria.list();

            for (User u: users) {
                allUsers.add(u);
            }

            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                log.error(e);
            }
        } finally {
            session.close();
        }
        return allUsers;
    }

    /**
     * Update a user in the database
     * @param user the user to be updated
     * @throws HibernateException
     */
    @Override
    public void updateUser(User user) throws HibernateException{

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(user);
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
     * Delete a user from the database
     * @param user the user to be deleted
     */
    @Override
    public void deleteUser(User user) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(user);
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
     * Add a user to the database
     * @param user the user to be added to the database
     * @return return the userId
     */
    @Override
    public int addUser(User user) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = 0;

        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(user);
            session.save(createUserRole(user));
            tx.commit();
            log.info("Added user: " + user + " with id of: " + user.getUserId());
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * integrate with the role
     * @param user the primary key in the role table
     * @return the role of the username
     */
    private Role createUserRole(User user) {

        Role usersRoles = new Role();
        usersRoles.setUserName(user.getUserName());
        usersRoles.setRole("regUser");
        return usersRoles;
    }

    /**
     * Get userId from userName
     * @param userName the name to get the userid for
     * @return the userId
     */
    public int getUserIdFromUserName(String userName) {
        UserDaoWithHibernate user = new UserDaoWithHibernate();
        List<User> allUsers = new ArrayList<User>();
        allUsers = user.getAllUsers();
        int userId = 0;
        for (User person : allUsers) {
            if(person.getUserName().equals(userName)){
                userId = person.getUserId();
                break;
            }
        }

        return userId;
    }
}
