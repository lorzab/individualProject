package entity;

import org.hibernate.annotations.Columns;

import javax.persistence.*;

/**
 * Created by Lora on 4/24/16.
 */
@Entity
@Table(name="book", schema = "BookshelfDB", catalog = "")
public class Book {

    private int id;
    private String isbn;
    private String title;
    private String author;

    public Book() {
    }

    public Book(int id, String isbn, String title, String author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isbn", nullable = true, length = 12)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 225)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 100)
    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "com.lorabahr.bookshelf.entity.Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

