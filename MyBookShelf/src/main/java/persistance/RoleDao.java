package persistance;

import entity.Role;

import java.util.List;

/**
 * Created by Lora on 2/17/16.
 */
public interface RoleDao {

    public List<Role> getAllRoles();
    public void updateRole(Role role);
    public void deleteRole(Role role);
    public String addRole(Role role);
}
