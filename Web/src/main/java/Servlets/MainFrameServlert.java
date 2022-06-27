package Servlets;

import Dao.DataStorage;
import Dao.LabelDataStorage;
import Model.Case;
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

public class MainFrameServlert extends HttpServlet {

    private static Date thisDay = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        List<Case> result = DataStorage.findeCaseByUserId(thisUser.getuserId());
        List<String> resultcolor = new ArrayList<String>();
        List<Boolean> isAGroupTask = new ArrayList<Boolean>();
        List<Case> resultMyCase = new ArrayList<Case>();
        Date date = null;
        String taskForDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat simpleDateFormatforDueDate = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
        if (thisDay == null) {
            try {
                date = sdf.parse(sdf.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            taskForDate = " today";
        } else {
            try {
                date = sdf.parse(sdf.format(thisDay.getTime()));
                thisDay = null;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            taskForDate = " " + sdf.format(date);
        }
        if (result != null) {
            for (Case mycase : result) {
                if (mycase != null) {
                    Date thisdate = null;
                    Date todaydate = null;
                    Date thisdatewithtime = null;
                    try {
                        thisdate = sdf.parse(sdf.format(mycase.getcaseDueDate().getTime()));
                        thisdatewithtime = simpleDateFormatforDueDate.parse(simpleDateFormatforDueDate.format(mycase.getcaseDueDate().getTime()));
                        todaydate = simpleDateFormatforDueDate.parse(simpleDateFormatforDueDate.format(new Date()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (thisdate.getTime() == date.getTime()) {
                        resultMyCase.add(mycase);
                        Label label = LabelDataStorage.findeLabelbyTask(mycase.getcaseNumber());
                        if(label.getGroupTask()==1)isAGroupTask.add(true);
                        else isAGroupTask.add(false);
                        if (thisdatewithtime.getTime() < (todaydate.getTime() + 3600000)) {
                            if (label != null){
                                resultcolor.add(label.getLabelColor() + " ; box-shadow: 0 0 15px #de4966");
                            }
                            else resultcolor.add("#4A538B ; box-shadow: 0 0 15px #de4966");
                        } else {
                            if ((todaydate.getTime() + 3600000) >= (thisdatewithtime.getTime() - (mycase.getPreparationTime().getTime() + 10800000))) {
                                if (label != null)
                                    resultcolor.add(label.getLabelColor() + " ; box-shadow: 0 0 15px #f8db71");
                                else resultcolor.add("#4A538B ; box-shadow: 0 0 15px #f8db71");
                            } else {
                                if (label != null) resultcolor.add(label.getLabelColor());
                                else resultcolor.add("#4A538B");
                            }
                        }
                    }
                }
            }
        }
        req.setAttribute("taskForDate", taskForDate);
        req.setAttribute("isAGroupTask", isAGroupTask);
        req.setAttribute("resultcolor", resultcolor);
        req.setAttribute("results", resultMyCase);
        req.getRequestDispatcher("/mainframe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("completeTheTask".equals(req.getParameter("actionType"))) {
            int caseNumber = Integer.parseInt(req.getParameter("DoneTaskButton"));
            Case thisCase = DataStorage.findeCase(caseNumber);
            if (thisCase != null) {
                thisCase.setCaseActive(0);
                DataStorage.UpdateCaseActive(thisCase);
            }
        } else if ("daySelection".equals(req.getParameter("actionType"))) {
            String day = req.getParameter("daySelection");
            try {
                thisDay = new SimpleDateFormat("dd.MM.yyyy").parse(day);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/mainframe");
    }

}
