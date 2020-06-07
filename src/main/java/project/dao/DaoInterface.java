package project.dao;

import java.util.List;

public interface DaoInterface<T> {
    List<T> getAll();

    boolean add(T object);

    boolean remove(T object);

    boolean update(T object);

    T getById(long id);
}
