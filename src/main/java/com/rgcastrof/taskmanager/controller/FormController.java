package com.rgcastrof.taskmanager.controller;

import com.rgcastrof.taskmanager.data.repository.TaskRepositoryImpl;
import com.rgcastrof.taskmanager.domain.models.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class FormController {
    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;

    private Task task;
    private TaskRepositoryImpl taskRepository = new TaskRepositoryImpl();

    private Consumer<Task> taskConsumer;
    public void setTaskConsumer(Consumer<Task> callback) {
        this.taskConsumer = callback;
    }

    public void setTask(Task t) {
        this.task = t;

        if (this.task != null) {
            nameField.setText(this.task.getName());
            descriptionField.setText(this.task.getDescription());
        }
    }

    @FXML
    private void saveForm() {
        String taskName = nameField.getText().trim();
        String taskDescription = descriptionField.getText().trim();

        if (this.task == null) {
            this.task = new Task(taskName, taskDescription, false);
        } else {
            this.task.setName(taskName);
            this.task.setDescription(taskDescription);
        }
        try {
            taskRepository.create(this.task);
            taskConsumer.accept(this.task);
            cancelForm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelForm() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
