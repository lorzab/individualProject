package entity;

import javax.persistence.Entity;

/**
 * Created by Lora on 2/17/16.
 */
@Entity
public class Role {

    private String userName;
    private String role;

    /**
     * Empty constructor.
     */
    public Role() {
    }

    /**
     * Constructor with both parameters
     *
     * @param userName the userName of the user
     * @param role what that user can access
     */
    public Role(String userName, String role) {
        this.role = role;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "com.lorabahr.bookshelf.entity.Role{" +
                "userName='" + userName + "'" +
                " ,role='" + role +
                "'}";
    }

}
