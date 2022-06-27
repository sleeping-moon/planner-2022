package Servlets;

import Dao.DataStorage;
import Dao.GroupDataStorage;
import Dao.LabelDataStorage;
import Dao.UserDataStorage;
import Model.Case;
import Model.Group;
import Model.Label;
import Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Dao.UserDataStorage.findeUserByLogin;

public class ProfileSettingsServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);
    private static String checksuccess = null;

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
        req.setAttribute("message", checksuccess);
        checksuccess = null;
        req.getRequestDispatcher("/profilesettings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        if ("save".equals(req.getParameter("Button"))) {
            String userName = req.getParameter("userName");
            String userLogin = req.getParameter("userLogin");
            String userPassword = req.getParameter("userPassword");
            String userOldPassword = req.getParameter("userOldPassword");
            String userPasswordRepeat = req.getParameter("userRepeatPassword");
            if (!req.getParameter("userPassword").isEmpty() && !req.getParameter("userRepeatPassword").isEmpty() && !req.getParameter("userOldPassword").isEmpty()) {
                if (thisUser.getuserPassword().equals(userOldPassword)) {
                    if (userPassword.equals(userPasswordRepeat) && userPassword.length() > 5) {
                        thisUser.setuserPassword(userPassword);
                        checksuccess = "Password successfully changed ";
                        LOGGER.info(thisUser.getuserLogin()+" changed the password");
                    } else {
                        checksuccess = "The password is not entered correctly, it must contain at least 6 characters and " +
                                "match the field 'Repeat password'";
                    }
                } else {
                    checksuccess = "The old password was not entered correctly";
                }
            }
            if (!req.getParameter("userLogin").isEmpty() && !thisUser.getuserLogin().equals(userLogin)) {
                if (findeUserByLogin(userLogin) == null) {
                    LOGGER.info(thisUser.getuserLogin()+" changed the login, new:"+userLogin);
                    thisUser.setuserLogin(userLogin);
                    checksuccess = "Login successfully changed ";
                } else {
                    checksuccess = "This login is already taken, try another one (((";
                }
            }
            if (!req.getParameter("userName").isEmpty() && !thisUser.getuserName().equals(userName)) {
                thisUser.setuserName(userName);
                checksuccess = "Name successfully changed ";
            }
            UserDataStorage.updateUser(thisUser);
        }
        if ("getOutOfAllGroups".equals(req.getParameter("Button"))) {
            List<Group> result = GroupDataStorage.findeAllGroupByUser(thisUser.getuserId());
            for (Group thisGroup : result) {
                if (thisUser.getuserId() != thisGroup.getGroupLeader()) {
                    GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), thisUser.getuserId());
                } else {
                    List<User> user = GroupDataStorage.readGroupMembers(thisGroup.getGroupId());
                    if (user.size() == 1) {
                        GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), thisUser.getuserId());
                        List<Integer> labels = LabelDataStorage.readTaskById(thisGroup.getGroupLabel());
                        if (labels != null) {
                            for (int i = 0; i < labels.size(); i++)
                                LabelDataStorage.deleteLabelTask(labels.get(i));
                        }
                        LabelDataStorage.deleteLabel(thisGroup.getGroupLabel());
                        GroupDataStorage.deleteGroup(thisGroup.getGroupId());
                    } else {
                        for (int i = 0; i < user.size(); i++) {
                            if (user.get(i).getuserId() != thisUser.getuserId()) {
                                thisGroup.setGroupLeader(user.get(i).getuserId());
                                GroupDataStorage.updateGroup(thisGroup);
                                GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), thisUser.getuserId());
                                break;
                            }
                        }
                    }
                }
            }
            checksuccess = "You successfully withdrew from all groups";
            LOGGER.info(thisUser.getuserLogin()+" withdrew from all groups");
        }
        if ("deleteProfile".equals(req.getParameter("Button"))) {
            List<Group> result = GroupDataStorage.findeAllGroupByUser(thisUser.getuserId());
            for (Group thisGroup : result) {
                if (thisUser.getuserId() != thisGroup.getGroupLeader()) {
                    GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), thisUser.getuserId());
                } else {
                    List<User> user = GroupDataStorage.readGroupMembers(thisGroup.getGroupId());
                    if (user.size() == 1) {
                        GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), thisUser.getuserId());
                        List<Integer> labels = LabelDataStorage.readTaskById(thisGroup.getGroupLabel());
                        if (labels.size() != 0) {
                            for (int i = 0; i < labels.size(); i++)
                                LabelDataStorage.deleteLabelTask(labels.get(i));
                        }
                        LabelDataStorage.deleteLabel(thisGroup.getGroupLabel());
                        GroupDataStorage.deleteGroup(thisGroup.getGroupId());
                        GroupDataStorage.deleteGroup(thisGroup.getGroupId());
                    } else {
                        for (int i = 0; i < user.size(); i++) {
                            if (user.get(i).getuserId() != thisUser.getuserId()) {
                                thisGroup.setGroupLeader(user.get(i).getuserId());
                                GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), thisUser.getuserId());
                                break;
                            }
                        }
                    }
                }
            }
            UserDataStorage.deleteUser(thisUser.getuserId());
            httpSession.removeAttribute("thisuser");
            LOGGER.info(thisUser.getuserLogin()+" deleted the profile");
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        }
        if ("deleteAllMyCases".equals(req.getParameter("Button"))) {
            List<Case> result = DataStorage.findeCaseToDelete(thisUser.getuserId());
            for (Case mycase : result) {
                DataStorage.deleteCase(mycase.getcaseNumber());
            }
            checksuccess = "You have successfully released from all your case)";
        }
        if ("updateAvatar1".equals(req.getParameter("Button"))) {
            thisUser.setUserPhoto("2a20ebb683f925f1882d62e582b14ee8.jpg");
            UserDataStorage.updateUser(thisUser);
            checksuccess = "Avatar update";
        }
        if ("updateAvatar2".equals(req.getParameter("Button"))) {
            thisUser.setUserPhoto("32a5d1422e2ad0282ebdc358bf6e76ab.jpg");
            UserDataStorage.updateUser(thisUser);
            checksuccess = "Avatar update";
        }
        if ("updateAvatar3".equals(req.getParameter("Button"))) {
            thisUser.setUserPhoto("463d58d9119021ac7d043ee1897b6213.jpg");
            UserDataStorage.updateUser(thisUser);
            checksuccess = "Avatar update";
        }
        if ("updateAvatar4".equals(req.getParameter("Button"))) {
            thisUser.setUserPhoto("c772867cd1988411690dac3bc5aea8ee.jpg");
            UserDataStorage.updateUser(thisUser);
            checksuccess = "Avatar update";
        }
        if ("updateAvatar5".equals(req.getParameter("Button"))) {
            thisUser.setUserPhoto("ed3f1938fcc1af63bc02e5c55ad943bb.jpg");
            UserDataStorage.updateUser(thisUser);
            checksuccess = "Avatar update";
        }
        if ("updateAvatar6".equals(req.getParameter("Button"))) {
            thisUser.setUserPhoto("no_avatar.jpg");
            UserDataStorage.updateUser(thisUser);
            checksuccess = "Avatar update";
        }
        resp.sendRedirect("/profilesettings");
    }
}
