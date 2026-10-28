package com.rgcastrof.taskmanager.domain.dao;

import java.util.List;

public interface TaskDao<T> {
    void create(T entity);
    void update(T entity);
    void complete(T entity);
    void delete(T entity);
    List<T> getAll();
    List<T> getByName(String pattern);
}
