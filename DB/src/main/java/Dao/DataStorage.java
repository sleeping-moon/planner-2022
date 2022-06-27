package Dao;

import Dao.DB.DBManager;
import Model.Case;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class DataStorage {

    private static final String SELECT_ALL = "select * from task";
    private static final String SELECT_CASE_BY_ID = "select * from task where case_id = ?";
    private static final String SELECT_CASE_BY_ID_AND_USER = "select * from task where case_id = ? and user_id =?";
    private static final String SELECT_CASE_BY_USER_ID = "select * from task where user_id =?";
    private static final String INSERT_CASE = "insert into Task(case_title,case_date,case_due_date,case_description,user_id,preparation_time) values (?,?,?,?,?,?)";
    private static final String UPDATE_CASE = "update task set case_title = ? , case_date = ? , case_due_date = ?,case_description = ?,preparation_time = ? where case_id = ?";
    private static final String UPDATE_CASE_ACTIVE = "update task set is_active = ? where case_id = ?";
    private static final String DELETE_CASE = "delete from task where case_id = ?";
    private static final String SELECT_CASE_BY_TITLE = "select * from task where case_title = ?";
    private static final String SELECT_CASE_TO_DELETE = "select t.case_title, t.case_id, t.user_id \n" +
            "from task t\n" +
            "join task_label tl on tl.case_id <> t.case_id \n" +
            "where  t.user_id = ? \n" +
            "except \n" +
            "select t.case_title, t.case_id, t.user_id \n" +
            "from labels l \n" +
            "join task_label tl on tl.label_id = l.label_id \n" +
            "join task t on tl.case_id = t.case_id \n" +
            "where l.group_task = 1 and t.user_id = ? \n" +
            "group by t.case_title  , t.case_id , t.user_id";

    private DataStorage() {
    }

    public static Case addCase(Case result) {
        try (Connection connection = DBManager.getConnection()) {
            Date sqlDate = new Date(result.getcaseDate().getTime());
            Timestamp sqlDueDate = new Timestamp(result.getcaseDueDate().getTime());
            Time time = new Time(result.getPreparationTime().getTime());
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CASE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, result.getcaseTitle());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setTimestamp(3, sqlDueDate);
            preparedStatement.setString(4, result.getcaseDescription());
            preparedStatement.setInt(5, result.getUserId());
            preparedStatement.setTime(6, time);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    result.setcaseNumber(resultSet.getInt("case_id"));
                }
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Case> readTask() throws ParseException {
        List<Case> result = new ArrayList<Case>();
        try (Connection connection = DBManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                result.add(initCase(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void deleteCase(int number) {
        LabelDataStorage.deleteTaskLabel(number);
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CASE);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Case findeCase(int id) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CASE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initCase(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Case findeCasebytitle(String name) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CASE_BY_TITLE);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initCase(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Case findeCaseByIdAndUser(int caseId, int userId) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CASE_BY_ID_AND_USER);
            preparedStatement.setInt(1, caseId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initCase(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Case> findeCaseByUserId(int userId) {
        List<Case> result = new ArrayList<Case>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CASE_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(initCase(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static List<Case> findeCaseToDelete(int userId) {
        List<Case> result = new ArrayList<Case>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CASE_TO_DELETE);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(initCase(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void updateCase(Case result) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CASE);
            preparedStatement.setString(1, result.getcaseTitle());
            Date sqlDate = new Date(result.getcaseDate().getTime());
            preparedStatement.setDate(2, sqlDate);
            Timestamp sqlDueDate = new Timestamp(result.getcaseDueDate().getTime());
            preparedStatement.setTimestamp(3, sqlDueDate);
            preparedStatement.setString(4, result.getcaseDescription());
            Time time = new Time(result.getPreparationTime().getTime());
            preparedStatement.setTime(5, time);
            preparedStatement.setInt(6, result.getcaseNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void UpdateCaseActive(Case result) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CASE_ACTIVE);
            preparedStatement.setInt(1, result.getCaseActive());
            preparedStatement.setInt(2, result.getcaseNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static Case initCase(ResultSet resultSet) throws SQLException{
        Case result = new Case();
        result.setcaseNumber(resultSet.getInt("case_id"));
        result.setcaseTitle(resultSet.getString("case_title"));
        result.setcaseDate(resultSet.getDate("case_date"));
        result.setcaseDueDate(resultSet.getTimestamp("case_due_date"));
        result.setcaseDescription(resultSet.getString("case_description"));
        result.setUserId(resultSet.getInt("user_id"));
        result.setPreparationTime(resultSet.getTimestamp("preparation_time"));
        result.setCaseActive(resultSet.getInt("is_active"));
        return result;
    }

    public static boolean ValidateDate(String date) {
        String[] parts = date.split("/");
        if (parts.length == 3) {
            if (parts[0].matches("[-+]?\\d+") && parts[1].matches("[-+]?\\d+") && parts[2].matches("[-+]?\\d+")) {
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);
                if (day < 32 && day > 0 && month < 13 && month > 0 && year > 0) {
                    if (day == 31 &&
                            (month == 4 || month == 6 || month == 9 ||
                                    month == 11)) return false; // only 1,3,5,7,8,10,12 has 31 days
                    else if (month == 2) {//leap year
                        if (year % 4 == 0) {
                            if (day == 30 || day == 31) return false;
                        } else {
                            return day < 29;
                        }
                    } else return false;
                } else return false;
            }
        }
        return false;
    }

    public static boolean ValidateDueDate(String due_date) {
        String[] parts = due_date.split(" ");
        if (parts.length == 2) {
            return parts[0].matches("[-+]?\\d+") && Integer.parseInt(parts[0]) > 0 && (parts[1].equals("день") || parts[1].equals("дня") || parts[1].equals("дней") ||
                    parts[1].equals("Дня") || parts[1].equals("Дней") || parts[1].equals("День"));
        }
        return false;
    }

}
