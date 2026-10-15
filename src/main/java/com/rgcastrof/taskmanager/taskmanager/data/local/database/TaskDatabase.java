package com.rgcastrof.taskmanager.taskmanager.data.local.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TaskDatabase {
    private static TaskDatabase instance;
    private final Connection connection;

    private TaskDatabase() {
            try {
                Properties properties = new Properties();
                FileInputStream inputStream = new FileInputStream("./external/configuration/database.properties");
                properties.load(inputStream);
                inputStream.close();
                try {
                    String url = properties.getProperty("jdbc.url");
                    String username = properties.getProperty("jdbc.username");
                    String password = properties.getProperty("jdbc.password");

                    this.connection = DriverManager.getConnection(url, username, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static TaskDatabase getInstance() throws SQLException {
        if (instance == null) {
            instance = new TaskDatabase();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
