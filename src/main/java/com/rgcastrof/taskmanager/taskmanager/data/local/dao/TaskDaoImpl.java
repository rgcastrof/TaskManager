package com.rgcastrof.taskmanager.taskmanager.data.local.dao;

import com.rgcastrof.taskmanager.taskmanager.data.local.database.TaskDatabase;
import com.rgcastrof.taskmanager.taskmanager.domain.dao.TaskDao;
import com.rgcastrof.taskmanager.taskmanager.domain.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao<Task> {
    @Override
    public void create(Task task) {
        try {
            Connection connection = TaskDatabase.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO task (name, description, is_completed) VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setBoolean(3, task.getCompleted());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try {
            Connection connection = TaskDatabase.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM task"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("is_completed")
                ));
            }
            preparedStatement.close();
            rs.close();
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public List<Task> getByName(String pattern) {
        List<Task> tasks = new ArrayList<>();
        try {
            Connection connection = TaskDatabase.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM task WHERE name ILIKE " + "'%" + pattern + "%'"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getBoolean("is_completed")
                ));
            }
            preparedStatement.close();
            rs.close();
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}