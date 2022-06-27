package Servlets;

import Dao.LabelDataStorage;
import Dao.UserDataStorage;
import Model.Label;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ForAdminServlet extends HttpServlet {

    private static String message = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> result = null;
        try {
            result = UserDataStorage.readUser();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        req.setAttribute("results", result);
        req.setAttribute("message", message);
        message = null;
        req.getRequestDispatcher("/foradmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("formUpdateUserActive".equals(req.getParameter("actionType"))) {
            int userId = Integer.parseInt(req.getParameter("updateUserActive"));
            User user = UserDataStorage.findeUser(userId);
            if (user != null)
                UserDataStorage.updateUserActive(user);
        }
        if ("addNewLabel".equals(req.getParameter("actionType"))) {
            String labelTitle = req.getParameter("labelTitle");
            String labelColor = req.getParameter("labelColor");
            Label newLabel = new Label();
            newLabel.setLabelTitle(labelTitle);
            newLabel.setGroupTask(0);
            newLabel.setLabelColor(labelColor);
            newLabel.setUserId(0);
            if (LabelDataStorage.addLabel(newLabel) != null) {
                message = "New label successfully created";
            } else {
                message = "Failed to create a new label";
            }
        }
        resp.sendRedirect("/foradmin");
    }
}
