package Dao;

import Dao.DB.DBManager;
import Model.User;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserDataStorage {
    private static final String SELECT_ALL = "select * from users";
    private static final String SELECT_USER_BY_ID = "select * from users where user_id = ?";
    private static final String INSERT_USER = "insert into users(user_name,user_login,user_password) values (?,?,?)";
    private static final String UPDATE_USER = "update users set user_name = ? , user_login = ? ,user_password = ?,user_role = ?,user_photo = ? where user_id = ?";
    private static final String DELETE_USER = "delete from users where user_id = ?";
    private static final String DELETE_USER_FROM_GROUP = "update users set group_id = ? where user_login = ?";
    private static final String UPDATE_USER_ACTIVE = "update users set is_active = ? where user_id = ?";
    private static final String SELECT_USER_BY_GROUP = "select * from user_workgroup where group_id= ?";
    private static final String SELECT_USER_BY_GROUP_AND_LEADER = "select * from users where group_id = ? and user_role = 2";
    private static final String SELECT_USER_BY_LOGIN = "select * from users where user_login = ?";
    private static final String SELECT_USER_BY_LOGIN_PASSWORD = "select * from users where user_login = ? and user_password = ?";


    private UserDataStorage() {
    }

    public static User addUser(User result) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, result.getuserName());
            preparedStatement.setString(2, result.getuserLogin());
            preparedStatement.setString(3, result.getuserPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                result.setuserId(resultSet.getInt("user_id"));
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<User> readUser() throws ParseException {
        List<User> result = new ArrayList<User>();
        try (Connection connection = DBManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                result.add(initUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void deleteUser(int number) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static User findeUser(int id) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initUser(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<User> findeUserByGroup(int id) {
        List<User> result = new ArrayList<User>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_GROUP);
            preparedStatement.setInt(1, id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(preparedStatement.toString());
            while (resultSet.next()) {
                result.add(initUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static User findeGroupLeader(int id) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_GROUP_AND_LEADER);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initUser(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static User findeUserByLogin(String name) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initUser(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static User findeUserByLoginAndPassword(String login, String password) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initUser(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static User updateUser(User result) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, result.getuserName());
            preparedStatement.setString(2, result.getuserLogin());
            preparedStatement.setString(3, result.getuserPassword());
            preparedStatement.setInt(4, result.getuserRole());
            preparedStatement.setString(5, result.getUserPhoto());
            preparedStatement.setInt(6, result.getuserId());
            preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean updateUserActive(User user) {
        try (Connection connection = DBManager.getConnection()) {
            int active = 0;
            if (user.getUserActive() == 1) {
                active = 0;
            } else {
                active = 1;
            }
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ACTIVE);
            preparedStatement.setInt(1, active);
            preparedStatement.setInt(2, user.getuserId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean deleteFromGroup(String login) {
        try (Connection connection = DBManager.getConnection()) {
            int nullgrop = 0;
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_FROM_GROUP);
            preparedStatement.setInt(1, nullgrop);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static User initUser(ResultSet resultSet) throws SQLException {
        User result = new User();
        result.setuserId(resultSet.getInt("user_id"));
        result.setuserName(resultSet.getString("user_name"));
        result.setuserLogin(resultSet.getString("user_login"));
        result.setuserPassword(resultSet.getString("user_password"));
        result.setuserRole(resultSet.getInt("user_role"));
        result.setUserActive(resultSet.getInt("is_active"));
        result.setUserPhoto(resultSet.getString("user_photo"));
        return result;
    }
}
