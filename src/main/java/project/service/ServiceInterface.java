package project.service;

import java.util.List;

public interface ServiceInterface<T> {

   List <T> getAll();

   boolean add(T object);

   boolean remove(T object);

   boolean update(T object);

   T getById(long id);

}
