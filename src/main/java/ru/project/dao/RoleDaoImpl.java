package ru.project.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.model.Role;

import javax.persistence.TypedQuery;



@Repository
public class RoleDaoImpl implements RoleDao {


    private SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Role getOne(long id) {
        TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery("FROM Role WHERE id = :id");
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
