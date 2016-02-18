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

    }

    @Override
    public void deleteRole(Role role) {

    }

    @Override
    public int addRole(Role role) {
        return 0;
    }
}
