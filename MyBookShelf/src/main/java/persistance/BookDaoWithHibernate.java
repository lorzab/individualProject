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

    /*public ArrayList<ArrayList<Map>> searchTitle(String title) {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();

        Map<String, Integer> bookId = new HashMap<String, Integer>();
        Map<String, String> titleMap = new HashMap<String, String>();
        Map<String, String> author = new HashMap<String, String>();
        ArrayList<Map> bookInfo = new ArrayList<Map>();
        ArrayList<ArrayList<Map>> books = new ArrayList<ArrayList<Map>>();

        for(Book book : allBooks) {

            log.info("book dao");

            if(book.getTitle().equals(title)){
                bookId.put("bookId", book.getId());
                titleMap.put("title", title);
                author.put("author", book.getAuthor());
                log.info(book.getId() + " " + book.getTitle() + " " + book.getAuthor());
                bookInfo.add(bookId);
                bookInfo.add(titleMap);
                bookInfo.add(author);

                books.add(bookInfo);
            }
        }

        return  books;
    }



    public ArrayList<ArrayList<Map>> searchAllBookTitles() {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();

        log.info("Number of books" +allBooks.size());

        Map<String, Integer> bookId = new HashMap<String, Integer>();
        Map<String, String> title = new HashMap<String, String>();
        Map<String, String> author = new HashMap<String, String>();
        ArrayList<Map> bookInfo = new ArrayList<Map>();
        ArrayList<ArrayList<Map>> books = new ArrayList<ArrayList<Map>>();

        for(Book book : allBooks) {
            bookId.put("bookId", book.getId());
            title.put("title", book.getTitle());
            author.put("author", book.getAuthor());
            //log.info(book.getId() + " " + book.getTitle() + " " + book.getAuthor());

            bookInfo.add(bookId);
            bookInfo.add(title);
            bookInfo.add(author);

            books.add(bookInfo);
        }

        return books;
    }*/

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
    public ArrayList<ArrayList> getNonApprovedBooks() {
        List<Book> allBooks = new ArrayList<Book>();
        allBooks = getAllBooks();
        ArrayList bookInfo = new ArrayList();
        ArrayList<ArrayList> nonApprovedBooks = new ArrayList<ArrayList>();

        for(Book book : allBooks) {
            if(book.getApproved() == 0) {
                int bookId = book.getId();
                String title = book.getTitle();
                String author = book.getAuthor();
                String isbn = book.getIsbn();

                log.info("in nonapproved books, id: " + bookId);

                bookInfo.add(bookId);
                bookInfo.add(title);
                bookInfo.add(author);
                bookInfo.add(isbn);

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

        log.info("bookId: " + bookId);
        log.info("title: " + title);

        ArrayList randomBook = new ArrayList();
        randomBook.add(bookId);
        randomBook.add(title);

        return randomBook;
    }
}
