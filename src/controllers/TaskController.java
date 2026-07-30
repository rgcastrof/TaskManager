package controllers;

import models.Task;
import exceptions.TaskException;
import models.TaskStatus;

import java.util.ArrayList;

public class TaskController {
    private final ArrayList<Task> tasks;

    public TaskController() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws TaskException {
        for (Task t : tasks) {
            if (t.getId() == task.getId())
                throw new TaskException("There already a task with this id.");
        }

        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void updateTask(int taskId, TaskStatus status) throws TaskException {
        if (validateTask(taskId)) tasks.get(taskId).setStatus(status); else throw new TaskException("Task not found.");
    }

    public void deleteTask(int taskId) throws TaskException {
        if (validateTask(taskId)) tasks.remove(taskId); else throw new TaskException("Task not found.");
    }

    private boolean validateTask(int taskId) {
        for (Task t : tasks) {
            if (t.getId() == taskId) {
                return true;
            }
        }
        return false;
    }
}
