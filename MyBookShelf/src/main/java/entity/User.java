package entity;

import javax.persistence.*;

/**
 * Created by Lora on 2/7/16.
 *
 * This is the javabean for the user of the Bookshelf
 */
@Entity
@Table(name="user", schema = "BookshelfDB", catalog = "")
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
    private String password; // at some point this should be hashed/salted, rather than plain text


    public User() {
    }

    public User(int id, String firstName, String lastName, String userName, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 25)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 25)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "email_address", nullable = true, length = 35)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 25)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "com.lorabahr.bookshelf.entity.User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
