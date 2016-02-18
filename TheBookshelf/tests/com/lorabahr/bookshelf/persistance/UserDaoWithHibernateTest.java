package com.lorabahr.bookshelf.persistance;

import com.lorabahr.bookshelf.entity.User;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Lora on 2/8/16.
 */
public class UserDaoWithHibernateTest {

    @Before
    public void setup() {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        int insertUserId = 0;

        //create user for test
        User getAllUser = new User();
        getAllUser.setFirstName("first");
        getAllUser.setLastName("last");
        getAllUser.setEmailAddress("IrockThis@gmail.com");
        getAllUser.setUserName("LLCoolJ");
        getAllUser.setPassword("password123");
        getAllUser.setId(0);
        insertUserId = dao.addUser(getAllUser);

    }

    @Test
    public void testGetAllUsers() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        List<User> users = dao.getAllUsers();

        assertTrue("There is the wrong amount in the list", users.size() > 0);
    }

    @Test
    public void testUpdateUser() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        User user = new User(3, "Lora", "Bahr", "lBahr", "lora@gmail.com", "hello");

        dao.updateUser(user);
        assertEquals("This is the wrong user", "Lora", user.getFirstName());
    }

    @Test
    public void testDeleteUser() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        User user = new User();
        int sizeBefore;
        int sizeAfter;
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmailAddress("IrockThis@gmail.com");
        user.setUserName("LLCoolJ");
        user.setPassword("password123");
        user.setId(1);
        sizeBefore = dao.getAllUsers().size();
        dao.deleteUser(user);
        sizeAfter = dao.getAllUsers().size();

        assertTrue("The user was not deleted", sizeBefore > sizeAfter);
    }

    @Test
    public void testAddUser() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        int insertUserId = 0;

        //create user to add
        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Stark");
        user.setEmailAddress("IronMan@gmail.com");
        user.setUserName("ironMan");
        user.setPassword("hello");
        user.setId(0);

        insertUserId = dao.addUser(user);

        assertTrue(insertUserId > 0);
    }
}