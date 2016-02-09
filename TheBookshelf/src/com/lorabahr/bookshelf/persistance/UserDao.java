package com.lorabahr.bookshelf.persistance;

import com.lorabahr.bookshelf.entity.User;

import java.util.*;

/**
 * Created by Lora on 2/8/16.
 */
public interface UserDao {

    public List<User> getAllUsers();
    public void updateUserFirstName(User user, String newFirstName);
    public void deleteUser(User user);
    public int addUser(User user);

}
