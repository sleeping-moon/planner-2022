package Servlets;

import Dao.DataStorage;
import Dao.GroupDataStorage;
import Dao.LabelDataStorage;
import Dao.UserDataStorage;
import Model.Case;
import Model.Group;
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


public class CaseServlet extends HttpServlet {
    private static int caseFilterChoice = 1;
    private static boolean thisIsNotUserGroup = true;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Label> resultlabel = new ArrayList<Label>();
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        String userName = null;
        List<Case> result = new ArrayList<Case>();
        if (httpSession.getAttribute("userIdForLeader") != null) {
            int userIdforLeader = (int) httpSession.getAttribute("userIdForLeader");
            User thisUserGroup = UserDataStorage.findeUser(userIdforLeader);
            Group thisGroup = GroupDataStorage.findeGroup(Integer.parseInt((String) httpSession.getAttribute("GroupId")));
            List<Case> resultAllCase = DataStorage.findeCaseByUserId(thisUserGroup.getuserId());
            if (thisGroup != null && thisUserGroup != null) {
                for (Case mycase : resultAllCase) {
                    Label label = LabelDataStorage.findeLabelbyTask(mycase.getcaseNumber());
                    if (label != null && label.getLabelId() == thisGroup.getGroupLabel()) {
                        result.add(mycase);
                        resultlabel.add(label);
                    }
                }
                thisIsNotUserGroup = false;
                userName = thisUserGroup.getuserName();
                int userId = thisUserGroup.getuserId();
                req.setAttribute("userId", userId);
                httpSession.setAttribute("userIdForLeader", null);
            }
        } else {
            userName = "My";
            thisIsNotUserGroup = true;
            if (thisUser != null) {
                if (caseFilterChoice == 1) {
                    List<Case> filterResult = DataStorage.findeCaseByUserId(thisUser.getuserId());
                    for (int i = 0; i < filterResult.size(); i++) {
                        if (filterResult.get(i).getCaseActive() == 1) result.add(filterResult.get(i));
                    }
                } else if (caseFilterChoice == 0) {
                    result = DataStorage.findeCaseByUserId(thisUser.getuserId());
                } else if (caseFilterChoice == 2) {
                    List<Case> filterResult = DataStorage.findeCaseByUserId(thisUser.getuserId());
                    for (int i = 0; i < filterResult.size(); i++) {
                        if (filterResult.get(i).getCaseActive() == 0) result.add(filterResult.get(i));
                    }
                }
                for (Case mycase : result) {
                    Label label = LabelDataStorage.findeLabelbyTask(mycase.getcaseNumber());
                    resultlabel.add(label);
                }
                caseFilterChoice = 1;
            }
        }
        httpSession.setAttribute("thisIsNotUserGroup", thisIsNotUserGroup);
        req.setAttribute("userName", userName);
        req.setAttribute("resultlabel", resultlabel);
        req.setAttribute("results", result);
        req.getRequestDispatcher("/case.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("Filter".equals(req.getParameter("actionType"))) {
            if ("active".equals(req.getParameter("caseFilter"))) {
                caseFilterChoice = 1;
            } else if ("allCases".equals(req.getParameter("caseFilter"))) {
                caseFilterChoice = 0;
            } else if ("closed".equals(req.getParameter("caseFilter"))) {
                caseFilterChoice = 2;
            }
        }
        resp.sendRedirect("/case");
    }
}



