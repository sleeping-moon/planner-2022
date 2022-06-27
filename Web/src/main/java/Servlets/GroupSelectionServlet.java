package Servlets;

import Dao.GroupDataStorage;
import Model.Group;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GroupSelectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        List<Group> result = null;
        List<Group> groupresult = new ArrayList<Group>();
        try {
            result = GroupDataStorage.readGroup();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (result != null) {
            for (Group user : result) {
                if (GroupDataStorage.findeGroupByUser(thisUser.getuserId(), user.getGroupId())) {
                    groupresult.add(user);
                }
            }
        }
        req.setAttribute("results", groupresult);
        req.getRequestDispatcher("/group_selection.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("GroupId", req.getParameter("GroupSelection"));
        resp.sendRedirect("/group");
    }
}
