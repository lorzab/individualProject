package com.lorabahr.bookshelf.persistance;

import com.lorabahr.bookshelf.entity.Role;

import java.util.List;

/**
 * Created by Lora on 2/17/16.
 */
public interface RoleDao {

    public List<Role> getAllRoles();
    public void updateRole(Role role);
    public void deleteRole(Role role);
    public int addRole(Role role);
}
