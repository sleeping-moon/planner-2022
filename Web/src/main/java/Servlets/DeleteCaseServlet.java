package Servlets;

import Dao.DataStorage;
import Dao.GroupDataStorage;
import Dao.LabelDataStorage;
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
import java.text.SimpleDateFormat;

public class DeleteCaseServlet extends HttpServlet {
    private static Case result = null;
    private static String checksuccess = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("checksuccess", checksuccess);
        checksuccess = null;
        Label resultLabel = null;
        SimpleDateFormat simpleDateFormatforDueDate = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
        SimpleDateFormat simpleDateFormatforTime = new SimpleDateFormat("HH:mm");
        if (result != null) {
            resultLabel = LabelDataStorage.findeLabelbyTask(result.getcaseNumber());
        }
        req.setAttribute("simpleDateFormatforDueDate", simpleDateFormatforDueDate);
        req.setAttribute("simpleDateFormatforTime", simpleDateFormatforTime);
        req.setAttribute("resultsCaseLabel", resultLabel);
        req.setAttribute("resultsDelete", result);
        req.getRequestDispatcher("/deletecase.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        if ("choice_of_case".equals(req.getParameter("actionType"))) {
            String caseNumber = req.getParameter("caseNumber");
            if (caseNumber.matches("[-+]?\\d+") && !caseNumber.isEmpty()) {
                result = DataStorage.findeCase(Integer.parseInt(caseNumber));
                if (result == null) {
                    checksuccess = "Case is not found";
                }else{
                    Label resultLabel = LabelDataStorage.findeLabelbyTask(result.getcaseNumber());
                    if(resultLabel != null) {
                        Group resultGroup = GroupDataStorage.findeGroupByLabel(resultLabel.getLabelId());
                        if ( result.getUserId() == thisUser.getuserId() && resultLabel.getGroupTask() == 1) {
                            result = null;
                            checksuccess = "You cannot edit group task";
                            resp.sendRedirect("/mainfraime");
                        } else if (resultGroup != null && resultLabel.getGroupTask() == 1 && resultGroup.getGroupLeader() != thisUser.getuserId()){
                            result = null;
                            checksuccess = "Case is not found";
                        }
                    }else if(result.getUserId() != thisUser.getuserId()){
                        result = null;
                        checksuccess = "Case is not found";
                    }
                }
            } else {
                checksuccess = "Case is not found";
            }
            resp.sendRedirect("/deletecase");
        } else {
            if ("yes".equals(req.getParameter("solution"))) {
                int this_case = result.getcaseNumber();
                DataStorage.deleteCase(this_case);
                result = null;
                checksuccess = null;
            } else if ("no".equals(req.getParameter("solution"))) {
                checksuccess = null;
                result = null;
            } else {
                checksuccess = "Failed to delete case";
            }
            resp.sendRedirect("/mainframe");
        }

    }
}
