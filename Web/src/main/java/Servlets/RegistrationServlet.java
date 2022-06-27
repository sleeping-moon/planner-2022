package Servlets;

import Dao.UserDataStorage;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Dao.UserDataStorage.findeUserByLogin;

public class RegistrationServlet extends HttpServlet {

    private static String checksuccess = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("checksuccessinregistration", checksuccess);
        checksuccess = null;
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        String userPasswordRepeat = req.getParameter("passwordRepeat");
        if (userPassword.equals(userPasswordRepeat) && userPassword.length() > 5) {
            if (findeUserByLogin(userLogin) == null) {
                User user = new User(userName, userLogin, userPassword);
                if (UserDataStorage.addUser(user) != null) {
                    checksuccess = null;
                    resp.sendRedirect("/authorization");
                } else {
                    checksuccess = "Failed to write to the database";
                }
            } else {
                checksuccess = "This login is already taken, try another one (((";
            }
        } else {
            checksuccess = "The password is not entered correctly, it must contain at least 6 characters and " +
                    "match the field 'Repeat password'";
        }
        resp.sendRedirect("/registration");
    }
}
