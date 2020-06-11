package project.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.model.User;


import java.util.List;

@Repository
public class UserDao implements Dao<User> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM User").getResultList();
    }

    @Override
    public boolean add(User object) {
        try {
            sessionFactory.getCurrentSession().save(object);
            return true;
        } catch (Exception e) {
            return false;
        }
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
}
