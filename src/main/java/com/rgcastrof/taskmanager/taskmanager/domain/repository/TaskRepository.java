package com.rgcastrof.taskmanager.taskmanager.domain.repository;

import java.util.List;

public interface TaskRepository<T> {
    void create(T entity);
    List<T> getAll();
    List<T> getByName(String pattern);
}
