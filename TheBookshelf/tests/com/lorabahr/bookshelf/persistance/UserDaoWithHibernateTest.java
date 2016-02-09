package com.lorabahr.bookshelf.persistance;

import com.lorabahr.bookshelf.entity.User;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Lora on 2/8/16.
 */
public class UserDaoWithHibernateTest {

    @Test
    public void testGetAllUsers() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        List<User> allUsers = dao.getAllUsers();
        assertTrue(allUsers.size() > 0);
    }

    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testDeleteUser() throws Exception {

    }

    @Test
    public void testAddUser() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        int insertUserId = 0;

        //create user to add
        User user = new User();
        user.setFirstName("Lora");
        user.setLastName("Bahr");
        user.setEmailAddress("lora@gmail.com");
        user.setUserName("lBahr");
        user.setPassword("hello");
        user.setId(0);

        insertUserId = dao.addUser(user);

        assertTrue(insertUserId > 0);
    }
}