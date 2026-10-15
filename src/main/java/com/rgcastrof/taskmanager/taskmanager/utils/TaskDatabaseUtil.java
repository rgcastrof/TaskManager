package com.rgcastrof.taskmanager.taskmanager.utils;

import com.rgcastrof.taskmanager.taskmanager.data.local.database.TaskDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDatabaseUtil {
    public static void createTables() {
        try {
            Connection connection = TaskDatabase.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS Task (" +
                "id SERIAL NOT NULL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "description VARCHAR(255)," +
                "is_completed BOOLEAN NOT NULL" +
                ");"
            );
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
