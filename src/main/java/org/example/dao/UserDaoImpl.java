package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() == null) {
            manager.persist(user);
        } else {
            manager.merge(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        if (user != null) {
            manager.remove(user);
        }
    }
}