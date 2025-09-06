package me.learn.now.repository;

import me.learn.now.model.ProgressStatus;
import me.learn.now.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// CHANGED: Extend JpaRepository to get CRUD methods for UserProgress
// extends JpaRepository<UserProgress, Long> → Spring Data repository with Long primary key
public interface UserProgressRepo extends JpaRepository<UserProgress, Long> {
    // kisi user ka saara progress nikaalne ka method
    List<UserProgress> findByUserIdAndStatus(Long userId, ProgressStatus status);

    // user ke saare progress entries
    List<UserProgress> findByUserId(Long userId);

    // specific topic par user ka progress - Fixed to use a custom JPQL query
    @Query("SELECT up FROM UserProgress up WHERE up.user.id = :userId AND up.topic.id = :topicId")
    Optional<UserProgress> findByUserIdAndTopicId(@Param("userId") Long userId, @Param("topicId") Long topicId);

    // Add missing query methods required by DashboardService

    // Find by user ID and progress percentage, ordered by last seen date
    List<UserProgress> findByUserIdAndProgressPercentageOrderByLastSeenAtDesc(Long userId, int progressPercentage);

    // Find by user ID and progress percentage between two values, ordered by last seen date
    List<UserProgress> findByUserIdAndProgressPercentageBetweenOrderByLastSeenAtDesc(Long userId, int minPercentage, int maxPercentage);
}
