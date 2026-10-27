package com.rgcastrof.taskmanager.controller;

import com.rgcastrof.taskmanager.data.repository.TaskRepositoryImpl;
import com.rgcastrof.taskmanager.domain.models.Task;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
        taskName.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getName())
        );

        taskDescription.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDescription())
        );

        taskStatus.setCellValueFactory(data ->
                new SimpleBooleanProperty(data.getValue().getCompleted())
        );

        tasks.addAll(taskRepository.getAll());

        taskTableView.setItems(tasks);
    }

    @FXML
    public void newTask() {
        openForm(null);
    }

    private void openForm(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rgcastrof/taskmanager/FormView.fxml"));
            Parent root = loader.load();
            FormController formController = loader.getController();
            formController.setTask(task);
            /* Atualiza tabela ao inserir nova tarefa na janela de formulário */
            formController.setTaskConsumer(tasks::add);

            Stage stage = new Stage();

            stage.setTitle(task == null ? "Nova Tarefa" : "Editar Tarefa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
