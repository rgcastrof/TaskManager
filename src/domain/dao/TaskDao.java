package domain.dao;

import java.util.List;

public interface TaskDao<T> {
    void create(T entity);
    List<T> getAll();
    List<T> getByName(String pattern);
}
