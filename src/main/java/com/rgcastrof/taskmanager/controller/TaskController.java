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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private TextField searchBar;

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
        searchBar.setPromptText("Buscar tarefa...");
    }

    @FXML
    private void newTask() { openForm(null); }

    @FXML
    private void editTask() {
        Task selected = taskTableView.getSelectionModel().getSelectedItem();
        if (selected != null)
            openForm(selected);
        else
            showAlert("Por favor selecione uma tarefa para editar.", AlertType.WARNING);
    }

    @FXML
    private void completeTask() {
        Task selected = taskTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                taskRepository.complete(selected);
                selected.setCompleted(true);
                int index = tasks.indexOf(selected);
                if (index >= 0)
                    tasks.set(index, selected);
                else
                    showAlert("Tarefa não encontrada", AlertType.ERROR);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
            showAlert("Por favor selecione uma tarefa", AlertType.WARNING);
    }

    @FXML
    private void deleteTask() {
        Task selected = taskTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                taskRepository.delete(selected);
                tasks.remove(selected);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
            showAlert("Por favor selecione uma tarefa", AlertType.WARNING);

    }

    @FXML
    private void searchTask() {
        String pattern = searchBar.getText().trim();
        if (!pattern.isEmpty()) {
            try {
                List<Task> filteredTasks = taskRepository.getByName(pattern);
                tasks.setAll(filteredTasks);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            tasks.setAll(taskRepository.getAll());
        }
    }

    private void openForm(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rgcastrof/taskmanager/FormView.fxml"));
            Parent root = loader.load();
            FormController formController = loader.getController();
            formController.setTask(task);
            /* Atualiza tabela ao inserir nova tarefa na janela de formulário */
            if (task == null)
                formController.setTaskConsumer(tasks::add);
            else
                formController.setTaskConsumer(t -> {
                    int index = tasks.indexOf(t);
                    if (index >= 0)
                        tasks.set(index, t);
                    else
                        showAlert("Tarefa não encontrada", AlertType.ERROR);
                });

            Stage stage = new Stage();

            stage.setTitle(task == null ? "Nova Tarefa" : "Editar Tarefa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        if (alertType == AlertType.WARNING) {
            alert.setTitle("Aviso");
            alert.setHeaderText(message);
        }
        else if (alertType == AlertType.ERROR) {
            alert.setTitle("Erro");
            alert.setHeaderText(message);
        }
        alert.showAndWait();
    }
}
