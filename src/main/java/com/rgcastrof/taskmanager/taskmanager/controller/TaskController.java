package com.rgcastrof.taskmanager.taskmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
