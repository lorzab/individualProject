package persistance;

import entity.Book;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


/**
 * Created by Lora on 4/24/16.
 */
public class BookDaoWithHibernateTest {

    @Before
    public void setup() {

        BookDaoWithHibernate dao = new BookDaoWithHibernate();
        int insertBookId = 0;

        //create book for test
        Book getAllBooks = new Book();
        getAllBooks.setTitle("Harry Potter");
        getAllBooks.setAuthor("JK Rowling");
        getAllBooks.setIsbn("this isbn");
        insertBookId = dao.addBook(getAllBooks);
    }

    @Test
    public void testGetAllBooks() throws Exception {

        BookDaoWithHibernate dao = new BookDaoWithHibernate();
        List<Book> books = dao.getAllBooks();

        assertTrue("There is the wrong amount in the list", books.size() > 0);
    }

    @Test
    public void testUpdateBook() throws Exception {

        BookDaoWithHibernate dao = new BookDaoWithHibernate();
        Book book = new Book(1, "ThisNewIsbn", "One Fish Two Fish", "Dr. Seuess");

        dao.updateBook(book);
        assertEquals("This is the wrong user", "One Fish Two Fish", book.getTitle());
    }

    /*@Test
    public void testDeleteBook() throws Exception {

        BookDaoWithHibernate dao = new BookDaoWithHibernate();
        Book book = new Book();
        int sizeBefore;
        int sizeAfter;
        book.setIsbn("this isbn");
        book.setAuthor("JK Rowling");
        book.setTitle("Harry Potter");
        book.setId(2);
        sizeBefore = dao.getAllBooks().size();
        dao.deleteBook(book);
        sizeAfter = dao.getAllBooks().size();

        assertTrue("The user was not deleted", sizeBefore > sizeAfter);
    }*/

    @Test
    public void testAddBook() throws Exception {

        BookDaoWithHibernate dao = new BookDaoWithHibernate();
        int insertBookId = 0;

        //create user to add
        Book book = new Book();
        book.setAuthor("Lora Bahr");
        book.setTitle("This is my life");
        book.setIsbn("HelloWorld");
        insertBookId = dao.addBook(book);

        assertTrue("Insert failed", insertBookId > 0);
    }
}
