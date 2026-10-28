package com.rgcastrof.taskmanager.domain.repository;

import java.io.IOException;
import java.util.List;

public interface TaskRepository<T> {
    void create(T entity) throws IOException;
    void update(T entity) throws IOException;
    void complete(T entity) throws IOException;
    List<T> getAll();
    List<T> getByName(String pattern);
}
