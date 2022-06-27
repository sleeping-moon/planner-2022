package Servlets;

import Dao.GroupDataStorage;
import Dao.LabelDataStorage;
import Dao.UserDataStorage;
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
import java.util.List;

public class GroupServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupServlet.class);
    private static String message = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        Group thisGroup = GroupDataStorage.findeGroup(Integer.parseInt((String) httpSession.getAttribute("GroupId")));
        List<User> result = GroupDataStorage.readGroupMembers(Integer.parseInt((String) httpSession.getAttribute("GroupId")));
        if (thisGroup != null) {
            req.setAttribute("groupname", thisGroup.getGroupName());
            req.setAttribute("groupdescription", thisGroup.getGroupDescription());
            req.setAttribute("groupleader", UserDataStorage.findeUser(thisGroup.getGroupLeader()));
            req.setAttribute("message", message);
        }
        message = null;
        req.setAttribute("results", result);
        req.getRequestDispatcher("/group.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        User thisUser = (User) httpSession.getAttribute("thisuser");
        Group thisGroup = GroupDataStorage.findeGroup(Integer.parseInt((String) httpSession.getAttribute("GroupId")));
        if ("formAddTaskForUser".equals(req.getParameter("actionType")) && thisGroup != null) {
            int userId = Integer.parseInt(req.getParameter("addTaskForUser"));
            if (thisUser.getuserId() == thisGroup.getGroupLeader() || thisUser.getuserId() == userId) {
                httpSession.setAttribute("userIdForLeader", userId);
            }
            resp.sendRedirect("/newcase");
        }
        if ("formViewTaskForUser".equals(req.getParameter("actionType"))) {
            int userId = Integer.parseInt(req.getParameter("viewTaskForUser"));
            if (thisGroup != null) {
                if (thisUser.getuserId() == thisGroup.getGroupLeader() || thisUser.getuserId() == userId) {
                    httpSession.setAttribute("userIdForLeader", userId);
                }
                resp.sendRedirect("/case");
            }
        }
        if ("formDeleteUserFromGroup".equals(req.getParameter("actionType"))) {
            int userId = Integer.parseInt(req.getParameter("deletelogin"));
            if (thisGroup != null) {
                if (thisUser.getuserId() == thisGroup.getGroupLeader() || thisUser.getuserId() == userId) {
                    if (userId != thisGroup.getGroupLeader()) {
                        GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), userId);
                        LOGGER.info(UserDataStorage.findeUser(userId).getuserLogin()+" delete From Group" +thisGroup.getGroupName());
                    } else {
                        message = "You cannot delete a group leader";
                    }
                }
                resp.sendRedirect("/group");
            }
        }
        if ("formAddUserFromGroup".equals(req.getParameter("actionType"))) {
            if (thisGroup != null) {
                if (thisUser.getuserId() == thisGroup.getGroupLeader()) {
                    String userlogin = req.getParameter("addUser");
                    User addUser = UserDataStorage.findeUserByLogin(userlogin);
                    if (addUser != null) {
                        if (!GroupDataStorage.findeGroupByUser(addUser.getuserId(), thisGroup.getGroupId())) {
                            GroupDataStorage.addUserWorkgroup(thisGroup.getGroupId(), addUser.getuserId());
                            LOGGER.info(addUser.getuserLogin()+" add From Group" +thisGroup.getGroupName());
                        } else {
                            message = "This user is already in the group";
                        }
                    } else {
                        message = "Failed to add a user (are you sure it exists?)";
                    }
                }
                resp.sendRedirect("/group");
            }
        }
        if ("formUpdateGroup".equals(req.getParameter("actionType"))) {
            if (thisGroup != null) {
                if ("submitButton".equals(req.getParameter("Button"))) {
                    if (!(req.getParameter("changeLeader").isEmpty()) && thisUser.getuserId() == thisGroup.getGroupLeader()) {
                        String userlogin = req.getParameter("changeLeader");
                        User changeUser = UserDataStorage.findeUserByLogin(userlogin);
                        if (changeUser != null) {
                            if (GroupDataStorage.findeGroupByUser(changeUser.getuserId(), thisGroup.getGroupId())) {
                                thisGroup.setGroupLeader(changeUser.getuserId());
                                LOGGER.info(thisGroup.getGroupName()+"(Id:"+thisGroup.getGroupId()+") was updated" );
                            } else {
                                message = "Failed to change the group leader, maybe this user is not in the group";
                            }
                        }
                    }
                    if (!req.getParameter("changeTitle").isEmpty()) {
                        String changeTitle = req.getParameter("changeTitle");
                        Label label = LabelDataStorage.findeLabel(thisGroup.getGroupLabel());
                        label.setLabelTitle(changeTitle);
                        LabelDataStorage.updateGroup(label);
                        thisGroup.setGroupName(changeTitle);
                    }
                    String changeDecription = req.getParameter("changeDecription");
                    thisGroup.setGroupDescription(changeDecription);
                    GroupDataStorage.updateGroup(thisGroup);
                    LOGGER.info(thisGroup.getGroupName()+"(Id:"+thisGroup.getGroupId()+") was updated" );
                    resp.sendRedirect("/group");
                }
                if ("deleteButton".equals(req.getParameter("Button"))) {
                    List<User> user = GroupDataStorage.readGroupMembers(thisGroup.getGroupId());
                    if (user.size() == 1) {
                        GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), thisUser.getuserId());
                    } else {
                        for (int i = 0; i < user.size(); i++) {
                            GroupDataStorage.deleteFromGroup(thisGroup.getGroupId(), user.get(i).getuserId());
                            GroupDataStorage.deleteGroup(thisGroup.getGroupId());
                            LOGGER.info(thisGroup.getGroupName()+"(Id:"+thisGroup.getGroupId()+") was deleted" );
                        }
                    }
                    List<Integer> labels = LabelDataStorage.readTaskById(thisGroup.getGroupLabel());
                    if (labels.size() != 0) {
                        for (int i = 0; i < labels.size(); i++)
                            LabelDataStorage.deleteLabelTask(labels.get(i));
                    }
                    LabelDataStorage.deleteLabel(thisGroup.getGroupLabel());
                    GroupDataStorage.deleteGroup(thisGroup.getGroupId());
                    resp.sendRedirect("/groupselection");
                }
            }
        }
    }

}
