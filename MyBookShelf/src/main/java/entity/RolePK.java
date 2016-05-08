package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Lora on 5/6/16.
 */
public class RolePK implements Serializable {
    private String userName;
    private String role;


    @Id
    @Column(name = "user_name", nullable = false, length = 25)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Id
    @Column(name = "user_role", nullable = false, length = 25)
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
