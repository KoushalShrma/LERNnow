package me.learn.now.repository;

import me.learn.now.model.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// CHANGED: Extend JpaRepository to get CRUD methods for ScoreCard
// extends JpaRepository<ScoreCard, Long> → Spring Data repository with Long primary key
public interface ScoreCardRepo extends JpaRepository<ScoreCard, Long> {
    // Hinglish: userId se scorecard nikaalne ka shortcut method - updated field names
    Optional<ScoreCard> findByUserId(Long userId);

    // Add missing query method required by DashboardService
    List<ScoreCard> findByUserIdOrderByCreatedAtDesc(Long userId);
}
