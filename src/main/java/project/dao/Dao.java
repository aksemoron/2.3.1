package project.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    boolean add(T object);

    boolean remove(T object);

    boolean update(T object);

    T getById(long id);

    boolean save(T object);
}
