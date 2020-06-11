package project.service;


import org.springframework.transaction.annotation.Transactional;
import project.dao.Dao;
import project.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {

    @Autowired
    private Dao dao;

    @Override
    @Transactional
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public boolean add(User object) {
        return dao.add(object);
    }

    @Override
    @Transactional
    public boolean remove(User object) {
        return dao.remove(object);
    }

    @Override
    @Transactional
    public boolean update(User object) {
        return dao.update(object);
    }

    @Override
    @Transactional
    public User getById(long id) {
        return (User) dao.getById(id);
    }
}
