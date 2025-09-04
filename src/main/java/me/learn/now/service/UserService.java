package me.learn.now.service;

import me.learn.now.model.User;
import me.learn.now.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo ur;

    @Autowired
    private PasswordEncoder passwordEncoder; // Hinglish: yahi se password ko hash karenge

    // Create user
    public User addUser(User user) {
        // Hinglish: plain text password kabhi store nahi karni, yaha hash kar diya
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
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

    // Update basic fields (name/email)
    public User updateUser(Long id, User user){
        User existingUser = ur.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return ur.save(existingUser);
    }

    // Change password safely
    public User updatePassword(Long id, String newPassword) {
        if (newPassword == null || newPassword.isBlank())
            throw new IllegalArgumentException("Password cannot be empty");

        User user = ur.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        // Hinglish: agar naya password same hai purane se (match ho gaya) toh error
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new RuntimeException("New password cannot be the same as the old password");
        }
        // Hinglish: hash karke store karo
        user.setPassword(passwordEncoder.encode(newPassword));
        return ur.save(user);
    }
}
