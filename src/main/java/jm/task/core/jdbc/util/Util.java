package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util  {

    private final String driverName = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/myprogramm";
    private final String username = "postgres";
    private final String password = "Sasha1995";
    private static Util instance = new Util();
    private Connection connection;

    private Util() {
    try {
        Class.forName(driverName);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}
    public static Util getInstance() {
        return instance;
    }

    public Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
// реализуйте настройку соеденения с БД

