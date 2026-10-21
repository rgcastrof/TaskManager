package com.rgcastrof.taskmanager;

import com.rgcastrof.taskmanager.utils.TaskDatabaseUtil;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        TaskDatabaseUtil.createTables();
        Application.launch(MainApplication.class, args);
    }
}
