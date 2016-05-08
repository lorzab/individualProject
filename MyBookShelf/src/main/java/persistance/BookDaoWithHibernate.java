package persistance;

import entity.Book;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lora on 4/24/16.
 */
public class BookDaoWithHibernate implements BookDao{

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    public List<Book> getAllBooks() {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<Book> allBooks = new ArrayList<Book>();
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Book.class);
            List<Book> books = criteria.list();

            for (Book b: books) {
                allBooks.add(b);
            }

            tx.commit();
        } catch (HibernateException e) {
            if(tx != null) {
                log.error(e);
            }
        } finally {
            session.close();
        }
        return allBooks;
    }

    @Override
    public void updateBook(Book book){
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteBook(Book book) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(book);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error(e);
        } finally {
            session.close();
        }
    }

    @Override
    public int addBook(Book book) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Integer id = 0;
        try {
            tx = session.beginTransaction();
            id = (Integer) session.save(book);
            tx.commit();
            //log.info("Added book: " + book + " with isbn of: " + book.getIsbn());
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            log.error(e);
        } finally {
            session.close();
        }
        return id;
    }

    /**
     * Get all the books by one author
     * @param author
     * @return
     */
    public ArrayList<Map<Integer, String>> getBooksByAuthor(String author) {
        List<Book> allBooks = new ArrayList<Book>();
        ArrayList<Map<Integer, String>> booksByAuthor = new ArrayList<Map<Integer, String>>();
        Map<Integer, String> idAuthor = new HashMap<Integer, String>();
        //get all books
        allBooks = getAllBooks();

        for(Book book : allBooks) {
            if(book.getAuthor().equals(author)){
                idAuthor.put(book.getId(), book.getTitle());
                booksByAuthor.add(idAuthor);
            }
        }

        return booksByAuthor;
    }

    /**
     * Search for one title
     */
    public ArrayList<Map<Integer ,Map<String, String>>> searchTitle(String title) {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();

        Map<Integer, Map<String, String>> bookId = new HashMap<Integer, Map<String, String>>();
        ArrayList<Map<Integer, Map<String, String>>> bookInfo = new ArrayList<Map<Integer, Map<String, String>>>();

        for(Book book : allBooks) {
            if(book.getTitle().equals(title)){
                Map<String, String> titleAuthor = new HashMap<String, String>();
                titleAuthor.put(title, book.getAuthor());
                //log.info(book.getId() + " " + title + " " + book.getAuthor());
                bookId.put(book.getId(), titleAuthor);
                bookInfo.add(bookId);
            }
        }

        return  bookInfo;
    }

    /**
     * Search all books on the shelves
     */
    public ArrayList<Map<Integer ,Map<String, String>>> searchAllBookTitles() {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();

        log.info("Number of books" +allBooks.size());

        Map<Integer,Map<String, String>> bookId = new HashMap<Integer, Map<String, String>>();
        ArrayList<Map<Integer, Map<String, String>>> bookInfo = new ArrayList<Map<Integer, Map<String, String>>>();

        for(Book book : allBooks) {
            Map<String, String> titleAuthor = new HashMap<String, String>();
            titleAuthor.put(book.getTitle(), book.getAuthor());
            //log.info(book.getId() + " " + book.getTitle() + " " + book.getAuthor());
            bookId.put(book.getId(), titleAuthor);
            bookInfo.add(bookId);
        }

        return bookInfo;
    }

    /**
     * Get book title from ID
     */
    public String getTitleFromId(int bookId) {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();
        String title = "";

        for(Book book : allBooks) {
            if(book.getId() == bookId) {
                title = book.getTitle();
                break;
            }
        }
        return title;
    }

    /**
     * Get book author from ID
     */
    public String getAuthorFromId(int bookId) {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();
        String author = "";

        for(Book book : allBooks) {
            if(book.getId() == bookId) {
                author = book.getAuthor();
                break;
            }
        }
        return author;
    }
}
