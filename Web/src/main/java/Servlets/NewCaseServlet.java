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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewCaseServlet extends HttpServlet {
    private static final boolean checkvalidate = false;
    private static String message = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", message);
        message = null;
        List<Label> resultlabel = LabelDataStorage.readLabel();
        List<Label> result = new ArrayList<Label>();
        if (resultlabel != null) {
            for (int i = 0; i < resultlabel.size(); i++) {
                if (resultlabel.get(i).getGroupTask() != 1)
                    result.add(resultlabel.get(i));
            }
        }
        req.setAttribute("resultlabel", result);
        req.getRequestDispatcher("/newcase.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        if (httpSession.getAttribute("userIdForLeader") != null) {
            int userIdforLeader = (int) httpSession.getAttribute("userIdForLeader");
            thisUser = UserDataStorage.findeUser(userIdforLeader);

        }
        Case new_task = new Case();
        try {
            if (thisUser != null) {
                String caseDueDate = req.getParameter("caseDueDate");
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy");
                String preparationTime = req.getParameter("preparationTime");
                Date date = sdf.parse(sdf.format(new Date()));
                new_task.setcaseTitle(req.getParameter("caseTitle"));
                new_task.setcaseDate(date);
                new_task.setcaseDueDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(caseDueDate));
                new_task.setcaseDescription(req.getParameter("caseDescription"));
                new_task.setUserId(thisUser.getuserId());
                new_task.setPreparationTime(new SimpleDateFormat("HH:mm").parse(preparationTime));
                if (new_task.getcaseDate().getTime() < new_task.getcaseDueDate().getTime()) {
                    new_task = DataStorage.addCase(new_task);
                    if (httpSession.getAttribute("userIdForLeader") != null) {
                        Group thisGroup = GroupDataStorage.findeGroup(Integer.parseInt((String) httpSession.getAttribute("GroupId")));
                        if (thisGroup != null && new_task != null) {
                            LabelDataStorage.addTaskLabel(thisGroup.getGroupLabel(), new_task.getcaseNumber());
                        }
                    } else {
                        if (!("none".equals(req.getParameter("selectLabel"))) && new_task != null) {
                            int labelId = Integer.parseInt(req.getParameter("selectLabel"));
                            LabelDataStorage.addTaskLabel(labelId, new_task.getcaseNumber());
                        }
                    }
                    httpSession.setAttribute("userIdForLeader", null);
                    message = "Case was successfully added";
                }
            } else {
                message = "Failed to create a task, maybe the due date was entered incorrectly";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/newcase");
    }
}
