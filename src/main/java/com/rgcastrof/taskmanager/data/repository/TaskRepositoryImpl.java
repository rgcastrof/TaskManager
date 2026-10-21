package com.rgcastrof.taskmanager.data.repository;

import com.rgcastrof.taskmanager.data.local.dao.TaskDaoImpl;
import com.rgcastrof.taskmanager.domain.models.Task;
import com.rgcastrof.taskmanager.domain.repository.TaskRepository;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository<Task> {
    private final TaskDaoImpl taskDao = new TaskDaoImpl();
    @Override
    public void create(Task task) {
        taskDao.create(task);
    }

    @Override
    public List<Task> getAll() { return taskDao.getAll(); }

    @Override
    public List<Task> getByName(String pattern) { return taskDao.getByName(pattern); }
}
