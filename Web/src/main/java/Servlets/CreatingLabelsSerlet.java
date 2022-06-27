package Servlets;

import Dao.LabelDataStorage;
import Model.Label;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatingLabelsSerlet extends HttpServlet {

    private static String message = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        List<Label> result = new ArrayList<>();
        if (thisUser.getuserRole() != 3) {
            result = LabelDataStorage.readFreeLabel(thisUser.getuserId());
        } else {
            result = LabelDataStorage.readNotGroupLabel();
        }
        req.setAttribute("results", result);
        req.setAttribute("message", message);
        message = null;
        req.getRequestDispatcher("/labels.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        if ("addNewLabel".equals(req.getParameter("actionType"))) {
            String labelTitle = req.getParameter("labelTitle");
            String labelColor = req.getParameter("labelColor");
            Label newLabel = new Label();
            newLabel.setLabelTitle(labelTitle);
            newLabel.setGroupTask(0);
            newLabel.setLabelColor(labelColor);
            newLabel.setUserId(thisUser.getuserId());
            if (LabelDataStorage.addLabel(newLabel) != null) {
                message = "New label successfully created";
            } else {
                message = "Failed to create a new label";
            }
        } else if ("deleteLabels".equals(req.getParameter("actionType"))) {
            int labelId = Integer.parseInt(req.getParameter("deleteLabelsId"));
            LabelDataStorage.deleteLabelTask(labelId);
            LabelDataStorage.deleteLabel(labelId);
        }
        resp.sendRedirect("/profilesettings");
    }
}
