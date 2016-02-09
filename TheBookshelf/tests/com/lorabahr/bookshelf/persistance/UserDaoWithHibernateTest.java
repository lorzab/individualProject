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
        //create user to add
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmailAddress("IrockThis@gmail.com");
        user.setUserName("LLCoolJ");
        user.setPassword("password123");
        user.setId(0);

        insertUserId = dao.addUser(user);

    }

    @Test
    public void testGetAllUsers() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();

        List<User> allUsers = dao.getAllUsers();

        assertEquals("There is the wrong amount in the list", 1, allUsers.size());
    }

    @Test
    public void testUpdateUserFirstName() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        String fName = "Kelli";
        User user = new User(1, "Lora", "Bahr", "lBahr", "lora@gmail.com", "hello");

        dao.updateUserFirstName(user, "Kelli");
        assertEquals("This is the wrong user", "Kelli", user.getFirstName());
    }

    @Test
    public void testDeleteUser() throws Exception {

        UserDaoWithHibernate dao = new UserDaoWithHibernate();
        User user = new User();
        user.setFirstName("first");
        user.setLastName("last");
        user.setEmailAddress("IrockThis@gmail.com");
        user.setUserName("LLCoolJ");
        user.setPassword("password123");
        user.setId(3);
        dao.deleteUser(user);

        //assert true that id 3 is empty?
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