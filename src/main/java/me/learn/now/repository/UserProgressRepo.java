package me.learn.now.repository;

import me.learn.now.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

// CHANGED: Extend JpaRepository to get CRUD methods for UserProgress
// extends JpaRepository<UserProgress, Long> → Spring Data repository with Long primary key
public interface UserProgressRepo extends JpaRepository<UserProgress, Long> {

}
