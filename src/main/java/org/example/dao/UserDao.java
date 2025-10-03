package org.example.dao;

import org.example.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(Long id);
    void saveUser(User user);
    void deleteUser(Long id);
}