module com.rgcastrof.taskmanager.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.rgcastrof.taskmanager to javafx.fxml;
    exports com.rgcastrof.taskmanager;
    exports com.rgcastrof.taskmanager.controller;
    exports com.rgcastrof.taskmanager.domain.models;
    opens com.rgcastrof.taskmanager.controller to javafx.fxml;
}