package com.rgcastrof.taskmanager.taskmanager.controller;

import com.rgcastrof.taskmanager.taskmanager.data.repository.TaskRepositoryImpl;
import com.rgcastrof.taskmanager.taskmanager.domain.models.Task;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class TaskController {
    @FXML
    private TableView<Task> taskTableView;

    @FXML
    private TableColumn<Task, String> taskName;

    @FXML
    private TableColumn<Task, String> taskDescription;

    @FXML
    private TableColumn<Task, Boolean> taskStatus;

    private final TaskRepositoryImpl taskRepository = new TaskRepositoryImpl();
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        List<Task> taskList = taskRepository.getAll();
        taskName.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName())
        );

        taskDescription.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDescription())
        );

        taskStatus.setCellValueFactory(data ->
                new SimpleBooleanProperty(data.getValue().getCompleted())
        );

        tasks.addAll(taskList);

        taskTableView.setItems(tasks);
    }
}
