package com.lorabahr.bookshelf.persistance;

import com.lorabahr.bookshelf.entity.Role;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 2/17/16.
 */
public class RoleDaoWithHibernate implements RoleDao{

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public List<Role> getAllRoles() {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<Role> allRoles = new ArrayList<Role>();

        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Role.class);
            List<Role> roles = criteria.list();

            for (Role r: roles) {
                allRoles.add(r);
            }

            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

        return allRoles;
    }

    @Override
    public void updateRole(Role role) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(role);
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
    public void deleteRole(Role role) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(role);
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
    public int addRole(Role role) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(role);
            tx.commit();
            log.info("Added role: " + role + " with id of: " + id);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return id;

    }


}
