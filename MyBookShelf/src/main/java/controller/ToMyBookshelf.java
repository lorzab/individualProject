package controller;

import org.apache.log4j.Logger;
import persistance.UserDaoWithHibernate;
import wsdl.QuoteofTheDay;
import wsdl.QuoteofTheDaySoap;
import wsdl.Quotes;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by Lora on 5/7/16.
 *
 * Takes you to the main page after log in with all of the options, gets userName from login
 */
@WebServlet(
        name = "goToMyBookshelf",
        urlPatterns = { "/goto-my-bookshelf" }
)
public class ToMyBookshelf extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the session object
        HttpSession session = request.getSession();

        //get they value of user name
        String userName = request.getRemoteUser();
        session.setAttribute("userName", userName);
        log.info(userName);

        //get the userId
        int userId = 0;
        UserDaoWithHibernate user = new UserDaoWithHibernate();
        userId = user.getUserIdFromUserName(userName);
        session.setAttribute("userId", userId);

        //get the quote of the day from soap webservice
        QuoteofTheDaySoap service = new QuoteofTheDay().getQuoteofTheDaySoap12();
        Quotes quote = new Quotes();
        quote = service.getQuote();

        String quoteForPage = quote.getQuoteOfTheDay();
        String quoteAuthor = quote.getAuthor();

        session.setAttribute("quoteOfDay", quoteForPage);
        session.setAttribute("quoteAuthor", quoteAuthor);

        String urlForward = "/jsp/myBookshelf.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(
                urlForward);
        dispatcher.forward(request, response);
    }
}
