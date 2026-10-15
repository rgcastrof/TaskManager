module com.rgcastrof.taskmanager.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.rgcastrof.taskmanager.taskmanager to javafx.fxml;
    exports com.rgcastrof.taskmanager.taskmanager;
    exports com.rgcastrof.taskmanager.taskmanager.controller;
    opens com.rgcastrof.taskmanager.taskmanager.controller to javafx.fxml;
}