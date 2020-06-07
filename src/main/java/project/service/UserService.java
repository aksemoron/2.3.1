package project.service;


import org.springframework.transaction.annotation.Transactional;
import project.dao.DaoInterface;
import project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private DaoInterface daoInterface;

    @Override
    @Transactional
    public List<User> getAll() {
        return daoInterface.getAll();
    }

    @Override
    @Transactional
    public boolean add(User object) {
        return daoInterface.add(object);
    }

    @Override
    @Transactional
    public boolean remove(User object) {
        return daoInterface.remove(object);
    }

    @Override
    @Transactional
    public boolean update(User object) {
        return daoInterface.update(object);
    }

    @Override
    @Transactional
    public User getById(long id) {
        return (User) daoInterface.getById(id);
    }
}
