package com.rgcastrof.taskmanager.domain.models;

public class Task {
    private int id;
    private String name;
    private String description;
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

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

}
