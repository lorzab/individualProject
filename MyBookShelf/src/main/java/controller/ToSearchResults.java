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
 * Created by Lora on 5/7/16.
 *
 * Gets the results from your search to be placed on the page
 */
@WebServlet(
        name = "goToSearchResults",
        urlPatterns = { "/warArchive/goto-search-results" }
)
public class ToSearchResults extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        //get they value of search wanted
        String searchType = (String) session.getAttribute("searchType");

        //create element to hold search results
        ArrayList<ArrayList> authorSearchResults = new ArrayList<ArrayList>();
        ArrayList<ArrayList<String>> allTitles = new ArrayList<ArrayList<String>>();
        BookDaoWithHibernate books = new BookDaoWithHibernate();

        if(searchType.equals("author")) {
            //get the author being searched add to search Results
            String author = request.getParameter("authorSearch");
            session.setAttribute("author", author);

            //get all books by author
            authorSearchResults = books.getBooksByAuthor(author);
            session.setAttribute("searchResults", authorSearchResults);
        } else if (searchType.equals("title")) {
            String title = request.getParameter("titileSearch");
            allTitles = books.searchTitle(title);
            log.info("in title search");
            session.setAttribute("searchResults", allTitles);
        } else {
            allTitles = books.searchAllBookTitles();
            session.setAttribute("searchResults", allTitles);
        }

        String urlForward = "/warArchive/jsp/searchResults.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
