package persistance;

import entity.Book;
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
    public ArrayList<ArrayList> getBooksByAuthor(String author) {
        List<Book> allBooks = new ArrayList<Book>();
        ArrayList<ArrayList> booksByAuthor = new ArrayList<ArrayList>();
        ArrayList authorInfo = new ArrayList();
        //get all books
        allBooks = getAllBooks();

        for(Book book : allBooks) {
            if(book.getAuthor().equals(author)){

                authorInfo.add(book.getId());
                authorInfo.add(book.getTitle());

                booksByAuthor.add(authorInfo);
            }
        }

        return booksByAuthor;
    }


    public ArrayList<ArrayList> searchTitle(String title) {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();

        ArrayList bookInfo = new ArrayList();
        ArrayList<ArrayList> books = new ArrayList<ArrayList>();

        for(Book book : allBooks) {

            log.info("book dao");

            if(book.getTitle().equals(title)){

                log.info(book.getId() + " " + book.getTitle() + " " + book.getAuthor());
                bookInfo.add(book.getId());
                bookInfo.add(title);
                bookInfo.add(book.getAuthor());

                books.add(bookInfo);
            }
        }

        return  books;
    }



    public ArrayList<ArrayList> searchAllBookTitles() {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();

        log.info("Number of books" +allBooks.size());

        ArrayList bookInfo = new ArrayList();
        ArrayList<ArrayList> books = new ArrayList<ArrayList>();

        for(Book book : allBooks) {
            //log.info(book.getId() + " " + book.getTitle() + " " + book.getAuthor());

            bookInfo.add(book.getId());
            bookInfo.add(book.getTitle());
            bookInfo.add(book.getAuthor());

            books.add(bookInfo);
        }

        return books;
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

    /**
     * Get nonapproved books for approval
     */
    public ArrayList<ArrayList<String>> getNonApprovedBooks() {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();

        ArrayList bookInfo = new ArrayList();
        ArrayList<ArrayList<String>> nonApprovedBooks = new ArrayList<ArrayList<String>>();

        for(Book book : allBooks) {
            if(book.getApproved() == 0) {

                log.info("in nonapproved books, id: " + book.getId());

                bookInfo.add(Integer.toString(book.getId()));
                bookInfo.add(book.getTitle());
                bookInfo.add(book.getAuthor());
                bookInfo.add(book.getIsbn());

                nonApprovedBooks.add(bookInfo);
            }
        }
        return nonApprovedBooks;
    }

    /**
     * Get a random book
     */
    public ArrayList getRandomBook() {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();
        int bookCount = allBooks.size();
        log.info("book count " +  bookCount);
        Book book = new Book();

        //get random number
        int randomNumber = (int) ( Math.random() * (bookCount - 1 ));

        //log.info(url);
        log.info("random number: " + randomNumber);

        book = allBooks.get(randomNumber);
        int bookId = book.getId();
        String title = book.getTitle();

        ArrayList randomBook = new ArrayList();
        randomBook.add(bookId);
        randomBook.add(title);

        return randomBook;
    }
}
