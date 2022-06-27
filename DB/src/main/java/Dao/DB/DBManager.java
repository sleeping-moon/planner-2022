package Dao.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    public static String URL = "jdbc:postgresql://localhost:5432/plannerDB";
    public static String USER = "postgres";
    public static String PASS = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
