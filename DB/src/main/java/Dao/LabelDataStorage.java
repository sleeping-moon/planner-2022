package Dao;

import Dao.DB.DBManager;
import Model.Group;
import Model.Label;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabelDataStorage {
    private static final String SELECT_ALL = "select * from labels";
    private static final String DELETE_LABEL = "delete from labels where label_id = ?";
    private static final String DELETE_TASKLABEL = "delete from task_label where  case_id =?";
    private static final String DELETE_LABELTASK = "delete from task_label where  label_id =?";
    private static final String INSERT_LABEL = "insert into labels(label_title,group_task,label_color,user_label) values (?,?,?,?)";
    private static final String INSERT_TASKLABEL = "insert into task_label(case_id,label_id) values (?,?)";
    private static final String UPDATE_LABEL = "update labels set label_title = ? where label_id = ? ";
    private static final String SELECT_LABEL_BY_ID = "select * from labels where label_id = ?";
    private static final String SELECT_LABEL_BY_TASK = "select * from task_label where case_id = ?";
    private static final String SELECT_TASK_BY_ID = "select * from task_label where label_id = ?";
    private static final String SELECT_FREE_LABEL = "select * from labels where (group_task = 0 or user_label = ?) and group_task = 0";
    private static final String SELECT_USER_LABEL = "select * from labels where group_task = 0 ";

    public static Label initLabel(ResultSet resultSet) throws SQLException {
        Label label = new Label();
        label.setLabelId(resultSet.getInt("label_id"));
        label.setLabelTitle(resultSet.getString("label_title"));
        label.setLabelColor(resultSet.getString("label_color"));
        label.setGroupTask(resultSet.getInt("group_task"));
        label.setUserId(resultSet.getInt("user_label"));
        return label;
    }

    public static Label findeLabel(int id) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LABEL_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return initLabel(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Label findeLabelbyTask(int id) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LABEL_BY_TASK);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return findeLabel(resultSet.getInt("label_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Label deleteLabel(int label) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LABEL);
            preparedStatement.setInt(1, label);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Label deleteTaskLabel(int caseId) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASKLABEL);
            preparedStatement.setInt(1, caseId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Label deleteLabelTask(int labelId) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LABELTASK);
            preparedStatement.setInt(1, labelId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Label> readLabel() {
        List<Label> label = new ArrayList<>();
        try (Connection connection = DBManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                label.add(initLabel(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    public static List<Label> readFreeLabel(int id) {
        List<Label> label = new ArrayList<>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FREE_LABEL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                label.add(initLabel(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    public static List<Integer> readTaskById(int id) {
        List<Integer> label = new ArrayList<>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TASK_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                label.add(resultSet.getInt("label_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    public static List<Label> readNotGroupLabel() {
        List<Label> label = new ArrayList<>();
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_LABEL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                label.add(initLabel(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return label;
    }

    public static void updateGroup(Label result) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LABEL);
            preparedStatement.setString(1, result.getLabelTitle());
            preparedStatement.setInt(2, result.getLabelId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Label addLabel(Label label) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LABEL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, label.getLabelTitle());
            preparedStatement.setInt(2, label.getGroupTask());
            preparedStatement.setString(3, label.getLabelColor());
            preparedStatement.setInt(4, label.getUserId());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    label.setLabelId(resultSet.getInt("label_id"));
                }
            }
            return label;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void addTaskLabel(int labelId, int caselId) {
        try (Connection connection = DBManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TASKLABEL);
            preparedStatement.setInt(1, caselId);
            preparedStatement.setInt(2, labelId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
