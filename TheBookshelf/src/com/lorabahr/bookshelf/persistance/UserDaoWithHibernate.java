package com.lorabahr.bookshelf.persistance;

import com.lorabahr.bookshelf.entity.User;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 2/8/16.
 */
public class UserDaoWithHibernate implements UserDao {

    private final Logger log = Logger.getLogger(this.getClass());

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
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return allUsers;
    }

    @Override
    public void updateUserFirstName(User user) throws HibernateException{

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
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

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
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public int addUser(User user) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(user);
            tx.commit();
            log.info("Added user: " + user + " with id of: " + id);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return id;
    }
}
