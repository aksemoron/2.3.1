package ru.project.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.model.User;


import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserUserDaoImpl implements UserDao<User> {



    private  SessionFactory sessionFactory;

    @Autowired
    public UserUserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAll() {

        /*sessionFactory.getCurrentSession().createQuery("SELECT p FROM User p JOIN p.roles c WHERE c.id = 2").getResultList();/*/
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct u from User u left join fetch u.roles")
                .getResultList();
    }



    @Override
    public boolean remove(User object) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id = :id");
            query.setParameter("id", object.getId());
            query.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public User findUserByEmail(String email) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE email = :e");
        query.setParameter("e", email);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public boolean update(User object) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public User getById(long id) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE id = :id",User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public boolean save(User object) {
        sessionFactory.getCurrentSession().save(object);
        return true;
    }
}
