package ru.project.service;

import java.util.List;

public interface UserService<T> {

   List <T> getAll();


   boolean remove(T object);

   boolean update(T object);

   T getById(long id);

   boolean save(T object);
}
