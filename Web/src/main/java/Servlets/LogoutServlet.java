package Servlets;

import Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User thisUser = (User) session.getAttribute("thisuser");
        LOGGER.info(thisUser.getuserLogin()+" logged out");
        session.removeAttribute("thisuser");
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
