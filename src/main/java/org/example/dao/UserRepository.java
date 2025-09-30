package org.example.dao;

import org.example.model.User;
import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void delete(Long id);
}