package Dao;

import Dao.DB.DBManager;
import Model.Group;
import Model.Label;
import Model.User;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GroupDataStorage {
    private static final String SELECT_ALL = "select * from workgroup";
    private static final String SELECT_GROUP_BY_USER = "select * from user_workgroup where user_id= ? and group_id = ?";
    private static final String SELECT_GROUP_BY_LABEL = "select * from workgroup where group_label = ?";
    private static final String SELECT_ALL_GROUP_BY_USER = "select * from user_workgroup where user_id= ? ";
    private static final String SELECT_USERS_BY_GROUP = "select user_id from user_workgroup where group_id = ?";
    private static final String SELECT_GROUP_BY_ID = "select * from workgroup where group_id = ? ";
    private static final String INSERT_GROUP = "insert into Workgroup(group_name,group_leader,group_description,group_label) values (?,?,?,?)";
    private static final String INSERT_GROUP_AND_USER = "insert into User_workgroup(group_id,user_id) values (?,?)";
    private static final String UPDATE_GROUP = "update workgroup set group_name = ?, group_leader = ?, group_description = ? where group_id = ? ";
    private static final String DELETE_GROUP = "delete from workgroup where group_id = ?";
    private static final String DELETE_FROM_GROUP = "delete from user_workgroup where group_id = ? and user_id = ?";

    public static Group addGroup(Group result) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, result.getGroupName());
            preparedStatement.setInt(2, result.getGroupLeader());
            preparedStatement.setString(3, result.getGroupDescription());
            Label label = new Label();
            label.setLabelTitle(result.getGroupName());
            label.setLabelColor("#958dff");
            label.setGroupTask(1);
            LabelDataStorage.addLabel(label);
            preparedStatement.setInt(4, label.getLabelId());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    result.setGroupId(resultSet.getInt("group_id"));
                }
            }
            addUserWorkgroup(result.getGroupId(), result.getGroupLeader());
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void addUserWorkgroup(int groupid, int userid) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP_AND_USER);
            preparedStatement.setInt(1, groupid);
            preparedStatement.setInt(2, userid);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Group> readGroup() throws ParseException {
        List<Group> result = new ArrayList<Group>();
        try (Connection connection = DBManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                result.add(initGroup(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static List<User> readGroupMembers(int id) {
        List<User> result = new ArrayList<User>();
        ArrayList<Integer> idArray = new ArrayList<>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_GROUP);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idArray.add(resultSet.getInt("user_id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (int i = 0; i < idArray.size(); i++) {
            result.add(UserDataStorage.findeUser(idArray.get(i)));
        }
        return result;
    }

    public static void deleteGroup(int number) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteFromGroup(int groupId, int userId) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_GROUP);
            preparedStatement.setInt(1, groupId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Group findeGroup(int id) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initGroup(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Group findeGroupByLabel(int id) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_BY_LABEL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initGroup(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean findeGroupByUser(int idUser, int idGroup) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_BY_USER);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idGroup);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(preparedStatement.toString());
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static List<Group> findeAllGroupByUser(int idUser) {
        List<Group> result = new ArrayList<Group>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROUP_BY_USER);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(findeGroup(resultSet.getInt("group_id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private static Group initGroup(ResultSet resultSet) throws SQLException {
        Group result = new Group();
        result.setGroupId(resultSet.getInt("group_id"));
        result.setGroupName(resultSet.getString("group_name"));
        result.setGroupLeader(resultSet.getInt("group_leader"));
        result.setGroupDescription(resultSet.getString("group_description"));
        result.setGroupLabel(resultSet.getInt("group_label"));
        return result;
    }

    public static Group updateGroup(Group result) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUP);
            preparedStatement.setString(1, result.getGroupName());
            preparedStatement.setInt(2, result.getGroupLeader());
            preparedStatement.setString(3, result.getGroupDescription());
            preparedStatement.setInt(4, result.getGroupId());
            preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
