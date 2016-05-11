package controller;

import entity.Book;
import org.apache.log4j.Logger;
import persistance.BookDaoWithHibernate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lora on 5/9/16.
 */
@WebServlet(
        name = "goToApprovedBooks",
        urlPatterns = { "/goto-approvedbooks" }
)
public class ToBooksApproved extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        BookDaoWithHibernate allBooks = new BookDaoWithHibernate();
        ArrayList<ArrayList<String>> nonApprovedBooks = new ArrayList<ArrayList<String>>();
        nonApprovedBooks = allBooks.getNonApprovedBooks();
        List<Book> bookSet = new ArrayList<Book>();
        bookSet = allBooks.getAllBooks();
        Book book = new Book();
        ArrayList<ArrayList> approvedBooks = new ArrayList<ArrayList>();
        ArrayList bookInfo = new ArrayList();

        //go throug the list of all nonapproved books
        for(ArrayList abook : nonApprovedBooks) {
            int bookId = (Integer) abook.get(0);
            String bookIdString = Integer.toString(bookId);
            String approveId = request.getParameter(bookIdString);

            log.info("bookIdString: " + bookIdString);
            log.info("approvedId: " + approveId);

            //check if any of the nonapproved books were approved
            if(bookIdString.equals(approveId)){

                log.info("in if bookIdString = approvedId");

                //look through books to find the book wanted and set info
                for (Book thisBook : bookSet) {
                    String idString = Integer.toString(thisBook.getId());

                    if(bookIdString.equals(idString)) {

                        log.info("Setting book attributes");

                        book.setAuthor(thisBook.getAuthor());
                        book.setApproved(1);
                        book.setId(thisBook.getId());
                        book.setIsbn(thisBook.getIsbn());
                        book.setTitle(thisBook.getTitle());

                        allBooks.updateBook(book);

                        //add to the session
                        bookInfo.add(thisBook.getId());
                        bookInfo.add(thisBook.getTitle());

                        approvedBooks.add(bookInfo);

                    }
                }

            }
        }

        session.setAttribute("approvedBooks", approvedBooks);
        String urlForward = "/jsp/booksApproved.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
