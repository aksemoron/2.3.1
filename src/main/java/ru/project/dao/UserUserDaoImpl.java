package ru.project.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.model.User;
import ru.project.util.HiberUtil;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserUserDaoImpl implements UserDao<User> {


    private SessionFactory sessionFactory;

    @Autowired
    public UserUserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct u from User u left join fetch u.roles")
                .getResultList();
    }


    @Override
    public boolean remove(User object) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", object.getId());
        query.executeUpdate();
        return true;
    }


    @Override
    public User findUserByEmail(String email) {
        return HiberUtil.getSingleResult(sessionFactory.getCurrentSession().createQuery("select distinct u from User u left join fetch u.roles where u.email=:e"));
    }

    @Override
    public boolean update(User object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User getById(long id) {
        return HiberUtil.getSingleResult(sessionFactory.getCurrentSession().createQuery("select distinct u from User u left join fetch u.roles where u.id =:id", User.class));
    }

    @Override
    @Transactional
    public boolean save(User object) {
        sessionFactory.getCurrentSession().save(object);
        return true;
    }
}
