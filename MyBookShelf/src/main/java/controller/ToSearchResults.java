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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Lora on 5/7/16.
 */
@WebServlet(
        name = "goToSearchResults",
        urlPatterns = { "/goto-search-results" }
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
        ArrayList<Map<Integer, String>> authorSearchResults = new ArrayList<Map<Integer, String>>();
        ArrayList<Map<Integer ,Map<String, String>>> allTitles = new ArrayList<Map<Integer ,Map<String, String>>>();
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

        String urlForward = "/jsp/searchResults.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
