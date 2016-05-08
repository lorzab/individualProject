package persistance;

import entity.Role;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 2/17/16.
 */
public class RoleDaoWithHibernate implements RoleDao {

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
    public String addRole(Role role) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        String userName = "empty";
        Serializable returnUser = null;

        try {
            tx = session.beginTransaction();
            userName = createUserRole(role).getUserName();
            session.save(createUserRole(role));
            tx.commit();
            log.info("Added user: " + userName);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }

        return userName;
    }

    private User createUserRole(Role role) {

        User usersRoles = new User();
        usersRoles.setUserName(role.getUserName());
        return usersRoles;
    }
}
