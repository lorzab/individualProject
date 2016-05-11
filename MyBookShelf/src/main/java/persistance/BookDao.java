package persistance;

import entity.Book;
import java.util.List;

/**
 * Created by Lora on 4/24/16.
 *
 */
public interface BookDao {

    public List<Book> getAllBooks();
    public void updateBook(Book book);
    public void deleteBook(Book book);
    public int addBook(Book book);
}
