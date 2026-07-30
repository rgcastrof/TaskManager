package controllers;

import models.Task;
import exceptions.TaskException;

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

    public void deleteTask(int taskId) throws TaskException {
        boolean taskExist = false;
        for (Task t : tasks) {
            if (t.getId() == taskId) {
                taskExist = true;
                break;
            }
        }

        if (taskExist) tasks.remove(taskId); else throw new TaskException("Task not found.");
    }
}
