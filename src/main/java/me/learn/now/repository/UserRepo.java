package me.learn.now.repository;

import me.learn.now.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CHANGED: Extend JpaRepository to get CRUD methods for User
// extends JpaRepository<User, Long> → Spring Data repository with Long primary key
public interface UserRepo extends JpaRepository<User, Long> {

    // Hinglish: email se user dhoondhne ke liye - authentication me use hoga
    Optional<User> findByEmail(String email);

    // Hinglish: username se user dhoondhne ke liye
    Optional<User> findByName(String username);

    // Hinglish: email ya username se check karna ki user exist karta hai ya nahi
    boolean existsByEmail(String email);
    boolean existsByName(String username);
}
