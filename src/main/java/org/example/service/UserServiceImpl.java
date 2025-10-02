package org.example.service;

import org.example.dao.UserRepository;
import org.example.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void saveUser(User user) {
        repo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repo.delete(id);
    }
}