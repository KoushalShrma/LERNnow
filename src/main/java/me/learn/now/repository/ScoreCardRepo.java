package me.learn.now.repository;

import me.learn.now.model.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

// CHANGED: Extend JpaRepository to get CRUD methods for ScoreCard
// extends JpaRepository<ScoreCard, Long> → Spring Data repository with Long primary key
public interface ScoreCardRepo extends JpaRepository<ScoreCard, Long> {

}
