package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final String driverName = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/myprogramm";
    private final String username = "postgres";
    private final String password = "Sasha1995";
    private static Util instance = new Util();
    private Connection connection;
    private SessionFactory sessionFactory;

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


    // реализуйте настройку соеденения с БД

    public SessionFactory sessionFactory() {
        if (sessionFactory() == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.username", username);
                properties.setProperty("hibernate.connection.password", password);
                properties.setProperty("hibernate.driver_class", driverName);
                properties.setProperty("hibernate.connection.url", url);
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry); //центральный набор сервисов используется в нутри хибернет, исп для транзакций, кеширования и тд
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return sessionFactory;
    }
}


