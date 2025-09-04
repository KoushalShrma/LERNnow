package me.learn.now.repository;

import me.learn.now.model.LearningPath;
import me.learn.now.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningPathRepo extends JpaRepository<LearningPath, Long> {

    // Hinglish: user ke saare learning paths nikaalne ke liye - updated field names
    List<LearningPath> findByUser(User user);

    // Hinglish: user ID se learning paths find karne ke liye
    List<LearningPath> findByUserId(Long userId);

    // Hinglish: public learning paths jo sabko dikhane hai
    List<LearningPath> findByIsPublicTrue();

    // Hinglish: specific purpose ke learning paths find karne ke liye
    List<LearningPath> findByPurpose(String purpose);

    // Hinglish: language ke basis pe filter karne ke liye
    List<LearningPath> findByLanguage(String language);

    // Hinglish: custom query - popular learning paths (public ones with more topics)
    @Query("SELECT lp FROM LearningPath lp WHERE lp.isPublic = true ORDER BY SIZE(lp.topics) DESC")
    List<LearningPath> findPopularPublicPaths();
}
