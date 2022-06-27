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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateCaseServlet extends HttpServlet {

    private static Case result = null;
    private static String checksuccess = null;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        Label resultLabel = null;
        String dueDateInputFormat = null;
        String preparationTime = null;
        List<Label> resultAllOpenLabels = new ArrayList<>();
        SimpleDateFormat simpleDateFormatforDueDate = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
        SimpleDateFormat simpleDateFormatforTime = new SimpleDateFormat("HH:mm");
        if (result != null) {
            dueDateInputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(result.getcaseDueDate());
            preparationTime = new SimpleDateFormat("HH:mm").format(result.getPreparationTime());
            resultLabel = LabelDataStorage.findeLabelbyTask(result.getcaseNumber());
            if (resultLabel != null) {
                if (resultLabel.getGroupTask() == 1) {
                    resultAllOpenLabels.add(resultLabel);
                } else {
                    resultAllOpenLabels = LabelDataStorage.readFreeLabel(thisUser.getuserId());
                    for (int i = 0; i < resultAllOpenLabels.size(); i++) {
                        if (resultAllOpenLabels.get(i).getLabelId() == resultLabel.getLabelId()) {
                            resultAllOpenLabels.remove(resultAllOpenLabels.get(i));
                            resultAllOpenLabels.add(0, resultLabel);
                            break;
                        }
                    }
                }
            } else {
                resultAllOpenLabels = LabelDataStorage.readFreeLabel(thisUser.getuserId());
            }
        }
        req.setAttribute("checksuccess", checksuccess);
        checksuccess = null;
        req.setAttribute("results", result);
        req.setAttribute("simpleDateFormatforDueDate", simpleDateFormatforDueDate);
        req.setAttribute("simpleDateFormatforTime", simpleDateFormatforTime);
        req.setAttribute("preparationTime", preparationTime);
        req.setAttribute("dueDateInputFormat", dueDateInputFormat);
        req.setAttribute("resultsCaseLabel", resultLabel);
        req.setAttribute("resultAllLabels", resultAllOpenLabels);
        req.getRequestDispatcher("/updatecase.jsp").forward(req, resp);
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
            resp.sendRedirect("/updatecase");
        } else {
            String caseTitle = req.getParameter("caseTitle");
            String caseDueDate = req.getParameter("caseDueDate");
            String caseDescription = req.getParameter("caseDescription");
            String preparationTime = req.getParameter("preparationTime");
            try {
                if (!caseTitle.isEmpty()) result.setcaseTitle(caseTitle);
                if (!caseDueDate.isEmpty()) {
                    Date newduedate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(caseDueDate);
                    if (newduedate.compareTo(result.getcaseDate()) > -1) result.setcaseDueDate(newduedate);
                }
                if (!caseDescription.isEmpty()) {
                    result.setcaseDescription(caseDescription);
                }
                if (!("none".equals(req.getParameter("selectLabel"))) && result != null) {
                    int labelId = Integer.parseInt(req.getParameter("selectLabel"));
                    LabelDataStorage.deleteTaskLabel(result.getcaseNumber());
                    LabelDataStorage.addTaskLabel(labelId, result.getcaseNumber());
                } else {
                    LabelDataStorage.deleteTaskLabel(result.getcaseNumber());
                }
                if (!caseDueDate.isEmpty()) {
                    result.setPreparationTime(new SimpleDateFormat("HH:mm").parse(preparationTime));
                }
                if (result.getcaseDate().getTime() < result.getcaseDueDate().getTime()) {
                    DataStorage.updateCase(result);
                    checksuccess = "Case successfully updated";
                } else {
                    checksuccess = "Failed to update case";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/updatecase");
        }

    }
}
