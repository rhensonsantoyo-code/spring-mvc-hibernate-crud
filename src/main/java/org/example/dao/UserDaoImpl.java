package org.example.dao;

import org.example.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class).list();
    }

    @Override
    public User getUserById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}