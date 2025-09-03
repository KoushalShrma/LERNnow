package me.learn.now.repository;

import me.learn.now.model.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// CHANGED: Extend JpaRepository to get CRUD methods for ScoreCard
// extends JpaRepository<ScoreCard, Long> → Spring Data repository with Long primary key
public interface ScoreCardRepo extends JpaRepository<ScoreCard, Long> {
    // userId se scorecard nikaalne ka shortcut method
    Optional<ScoreCard> findBySUser_UId(Long uId);
}
