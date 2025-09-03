package me.learn.now.repository;

import me.learn.now.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// CHANGED: Extend JpaRepository to get CRUD methods for UserProgress
// extends JpaRepository<UserProgress, Long> → Spring Data repository with Long primary key
public interface UserProgressRepo extends JpaRepository<UserProgress, Long> {
    // kisi user ka saara progress nikaalne ka method
    List<UserProgress> findByUPuser_UId(Long uId);

    // specific topic par user ka progress
    Optional<UserProgress> findByUPuser_UIdAndUPtopic_TId(Long uId, Long tId);
}
