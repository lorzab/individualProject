package persistance;

import entity.Role;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lora on 4/24/16.
 */
public class RoleDaoWithHibernateTest {

    @Before
    public void setup() {

        RoleDaoWithHibernate dao = new RoleDaoWithHibernate();
        String insertRoleId = null;

        //create user for test
        Role getAllRoles = new Role();
        getAllRoles.setRole("admin");
        getAllRoles.setUserName("ironman");
        insertRoleId = dao.addRole(getAllRoles);
    }

    @Test
    public void testGetAllRoles() throws Exception {

        RoleDaoWithHibernate dao = new RoleDaoWithHibernate();
        List<Role> roles = dao.getAllRoles();

        assertTrue("There is the wrong amount in the list", roles.size() > 0);
    }

    @Test
    public void testUpdateRole() throws Exception {

        RoleDaoWithHibernate dao = new RoleDaoWithHibernate();
        Role role = new Role("ironman", "regUser");
        dao.updateRole(role);

        assertEquals("This is the wrong user", "regUser", role.getRole());
    }

    /*@Test
    public void testDeleteRole() throws Exception {

        RoleDaoWithHibernate dao = new RoleDaoWithHibernate();
        Role role = new Role();
        int sizeBefore;
        int sizeAfter;
        role.setUserName("ironman");
        role.setRole("admin");
        sizeBefore = dao.getAllRoles().size();
        dao.deleteRole(role);
        sizeAfter = dao.getAllRoles().size();

        assertTrue("The user was not deleted", sizeBefore > sizeAfter);
    }*/

    @Test
    public void testAddRole() throws Exception {

        RoleDaoWithHibernate dao = new RoleDaoWithHibernate();
        String insertRoleId = null;

        //create user to add
        Role role = new Role();
        role.setRole("admin");
        role.setUserName("lBahr");
        insertRoleId = dao.addRole(role);

        assertTrue("Insert failed", !insertRoleId.equals("empty"));
    }
}
