package entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;

/**
 * Created by Lora on 4/24/16.
 */
public class UserReadingList {

    private int reading_id;
    private int user_id;
    private int book_id;
    private int wish_list;
    private String date_added;

    public UserReadingList() {
    }

    public UserReadingList(int reading_id, int user_id, int book_id, int wish_list, String date_added) {
        this.reading_id = reading_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.wish_list = wish_list;
        this.date_added = date_added;
    }

    public int getReading_id() {
        return reading_id;
    }

    public void setReading_id(int reading_id) {
        this.reading_id = reading_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getWish_list() {
        return wish_list;
    }

    public void setWish_list(int wish_list) {
        this.wish_list = wish_list;
    }

    public String getDate_added (){return date_added;}

    @Temporal(TemporalType.DATE)
    public void setDate_added(String date_added) { this.date_added = date_added; }

    @Override
    public String toString() {
        return "com.lorabahr.bookshelf.entity.UserReadingList{" +
                "readingId=" + reading_id +
                ", userId='" + user_id + '\'' +
                ", bookId='" + book_id + '\'' +
                ", dateAdded= " + date_added +
                '}';
    }
}
