package me.learn.now.service;

import me.learn.now.model.User;
import me.learn.now.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo ur;

    public User addUser(User user) {
        return ur.save(user);
    }

    public Optional<User> getUser(Long id) {
        return ur.findById(id);
    }

    public List<User> getUsers() {
        return ur.findAll();
    }
}
