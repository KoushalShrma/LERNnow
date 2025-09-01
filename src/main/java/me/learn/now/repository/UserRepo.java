package me.learn.now.repository;

import me.learn.now.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CHANGED: Extend JpaRepository to get CRUD methods for User
// extends JpaRepository<User, Long> → Spring Data repository with Long primary key
public interface UserRepo extends JpaRepository<User, Long> {

}
