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
 * Gets the books that need to be approved by the admin role
 */
@WebServlet(
        name = "goToBookApproveBooks",
        urlPatterns = { "warArchive/goto-approve-books" }
)
public class TpApproveBooks extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        //get books that have not been approved yet
        ArrayList<ArrayList<String>> nonApprovedBooks = new ArrayList<ArrayList<String>>();
        BookDaoWithHibernate books = new BookDaoWithHibernate();
        nonApprovedBooks = books.getNonApprovedBooks();

        session.setAttribute("nonApproved", nonApprovedBooks);

        String urlForward = "warArchive/jsp/approveBooks.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
