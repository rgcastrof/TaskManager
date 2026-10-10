package data.repository;

import data.local.dao.TaskDaoImpl;
import domain.models.Task;
import domain.repository.TaskRepository;

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
