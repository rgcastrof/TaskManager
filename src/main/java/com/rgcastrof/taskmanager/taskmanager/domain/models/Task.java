package com.rgcastrof.taskmanager.taskmanager.domain.models;

public class Task {
    private final int id;
    private final String name;
    private final String description;
    private boolean isCompleted;

    public Task(int id, String name, String description, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

}
