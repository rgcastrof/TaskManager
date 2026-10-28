package com.rgcastrof.taskmanager;

import com.rgcastrof.taskmanager.data.local.database.TaskDatabase;
import javafx.application.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Launcher {
    static void main(String[] args) {
        try (Connection connection = TaskDatabase.getInstance().getConnection()) {
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
            Application.launch(MainApplication.class, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
