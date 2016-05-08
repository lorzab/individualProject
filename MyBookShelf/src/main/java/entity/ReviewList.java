package entity;

import javax.persistence.*;

/**
 * Created by Lora on 5/1/16.
 */
@Entity
@Table(name = "reviewList", schema = "BookshelfDB")
public class ReviewList {

    private int review_id;
    private int reading_id;
    private int user_id;
    private int book_id;
    private String notes;
    private double rating;


    public ReviewList() {
    }

    public ReviewList(int review_id, int reading_id, int user_id, int book_id, String notes, double rating) {
        this.review_id = review_id;
        this.reading_id = reading_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.notes = notes;
        this.rating = rating;
    }

    @Id
    @Column(name = "review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getReview_id() {return review_id;}

    public void setReview_id(int review_id) {this.review_id = review_id;}



    public int getReading_id() {
        return reading_id;
    }

    public void setReading_id(int reading_id) {this.reading_id = reading_id;}

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

    public String getNotes() { return notes; }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getRating() {return rating;}

    public void setRating(double rating) {this.rating = rating;}

    @Override
    public String toString() {
        return "com.lorabahr.bookshelf.entity.ReviewList{" +
                "reviewId=" + review_id +
                ", readingId=" + reading_id +
                ", userId='" + user_id + '\'' +
                ", bookId='" + book_id + '\'' +
                ", rating= " + rating +
                '}';
    }
}
