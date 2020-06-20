package project.dao;

import java.util.List;

public interface UserDao<T> {
    List<T> getAll();


    boolean remove(T object);

    boolean update(T object);

    T getById(long id);

    boolean save(T object);

    T findUserByEmail(String email);
}
