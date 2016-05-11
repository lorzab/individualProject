package controller;

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

/**
 * Created by Lora on 5/9/16.
 *
 * After you have chosen a random book this takes you to the page to render what book was provided
 */
@WebServlet(
        name = "goToRandomBook",
        urlPatterns = { "warArchive/goto-random-book" }
)
public class ToRandomBook extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        //get a random book
        BookDaoWithHibernate allBooks = new BookDaoWithHibernate();
        ArrayList bookInfo = new ArrayList();
        bookInfo = allBooks.getRandomBook();

        //get book id
        int bookId = (Integer) bookInfo.get(0);
        session.setAttribute("bookId", bookId);

        //get title
        String title = (String) bookInfo.get(1);
        session.setAttribute("book", title);

        String urlForward = "warArchive/jsp/randomBook.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }

}
