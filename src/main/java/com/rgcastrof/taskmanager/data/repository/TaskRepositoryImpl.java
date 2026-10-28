package com.rgcastrof.taskmanager.data.repository;

import com.rgcastrof.taskmanager.data.local.dao.TaskDaoImpl;
import com.rgcastrof.taskmanager.domain.models.Task;
import com.rgcastrof.taskmanager.domain.repository.TaskRepository;

import java.io.IOException;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository<Task> {
    private final TaskDaoImpl taskDao = new TaskDaoImpl();
    @Override
    public void create(Task task) throws IOException {
        taskDao.create(task);
    }

    @Override
    public void update(Task task) throws IOException {
        taskDao.update(task);
    }

    @Override
    public void complete(Task task) throws IOException {
        taskDao.complete(task);
    }

    @Override
    public List<Task> getAll() { return taskDao.getAll(); }

    @Override
    public List<Task> getByName(String pattern) { return taskDao.getByName(pattern); }
}
