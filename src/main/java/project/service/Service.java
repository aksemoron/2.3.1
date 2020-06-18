package project.service;

import java.util.List;

public interface Service<T> {

   List <T> getAll();

   boolean add(T object);

   boolean remove(T object);

   boolean update(T object);

   T getById(long id);

   boolean save(T object);
}
