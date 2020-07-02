package ru.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.dao.UserDao;
import ru.project.dao.RoleDao;
import ru.project.model.Role;
import ru.project.model.User;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserUserServiceImpl implements UserService<User>, UserDetailsService {


    private final UserDao userDao;


    private final RoleDao roleDao;

    @Autowired
    public UserUserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }


    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    @Transactional
    public boolean remove(User object) {
        return userDao.remove(object);
    }

    @Override
    @Transactional
    public boolean update(User object) {
        PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
        object.setPassword(passwordEnocder.encode(object.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(object.getBalans()));
        object.setRoles(roles);
        return userDao.update(object);
    }

    @Override
    @Transactional
    public User getById(long id) {
        return (User) userDao.getById(id);
    }

    @Override
    @Transactional
    public boolean save(User object) {
        PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
        object.setPassword(passwordEnocder.encode(object.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleDao.getOne(object.getBalans()));
            object.setRoles(roles);
        return userDao.save(object);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (User) userDao.findUserByEmail(email);

        if (user == null) {
            throw new NoResultException("No user with such email " + email);
        }

        return user;
    }
}
