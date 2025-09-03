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

    public Optional<User> deleteUser(Long id) {
        Optional<User> u = ur.findById(id);
        u.ifPresent(ur::delete);
        return u;
    }

    public User updateUser(Long id, User user){
        User existingUser = ur.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setuName(user.getuName());
        existingUser.setuEmail(user.getuEmail());
        return ur.save(existingUser);
    }

    public User updatePassword(Long id, String newPassword) {
        Optional<User> existingUser = ur.findById(id);
        if(existingUser.isPresent()) {
            User user = existingUser.get();
            if(user.getuPass().equals(newPassword)){
                throw new RuntimeException("New password cannot be the same as the old password");
            }
            user.setuPass(newPassword);
            return ur.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
