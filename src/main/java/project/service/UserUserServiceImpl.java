package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.UserDao;
import project.dao.RoleDao;
import project.model.Role;
import project.model.User;

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
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
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
