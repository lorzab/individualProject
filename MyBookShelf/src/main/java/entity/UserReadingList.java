package entity;

/**
 * Created by Lora on 4/24/16.
 */
public class UserReadingList {

    private int reading_id;
    private int user_id;
    private int book_id;
    private int wish_list;

    public UserReadingList() {
    }

    public UserReadingList(int reading_id, int user_id, int book_id, int wish_list) {
        this.reading_id = reading_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.wish_list = wish_list;
    }

    public int getReadingId() { return reading_id; }

    public void setReadingId(int reading_id) {
        this.reading_id = reading_id;
    }

    public int getUserId() { return user_id; }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getBookId() { return book_id; }

    public void setBookId(int book_id) {
        this.book_id = book_id;
    }

    public int getWishList() { return wish_list; }

    public void setWishList(int wish_list) {
        this.wish_list = wish_list;
    }

    @Override
    public String toString() {
        return "com.lorabahr.bookshelf.entity.UserReadingList{" +
                "readingId=" + reading_id +
                ", userId='" + user_id + '\'' +
                ", bookId='" + book_id + '\'' +
                '}';
    }
}
