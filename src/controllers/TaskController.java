package controllers;

import models.Task;

import java.util.ArrayList;

public class TaskController {
    private final ArrayList<Task> tasks;

    public TaskController() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        for (Task t : tasks) {
            if (t.getId() == task.getId())
                throw new RuntimeException("Error: There already a task with this id.");
        }

        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int taskId) {
        for (Task t : tasks) {
            if (t.getId() == taskId) {
                tasks.remove(taskId);
            }
        }
    }
}
