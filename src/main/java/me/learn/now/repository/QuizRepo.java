package me.learn.now.repository;

import me.learn.now.model.Difficulty;
import me.learn.now.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz,Long>{
    // Hinglish: topic ke basis pe quizzes find karne ke liye
    List<Quiz> findByTopicId(Long topicId);

    // Hinglish: topic ke active quizzes dhoondhne ke liye - updated field names
    List<Quiz> findByTopicIdAndIsActiveTrue(Long topicId);

    // Hinglish: difficulty level ke basis pe active quizzes filter karne ke liye
    List<Quiz> findByDifficultyAndIsActiveTrue(Difficulty difficulty);

    // Hinglish: language ke basis pe quizzes find karne ke liye
    List<Quiz> findByLanguageAndIsActiveTrue(String language);

    // Hinglish: purpose ke basis pe quizzes (JOB_INTERVIEW, EXAM, LEARNING etc)
    List<Quiz> findByPurposeAndIsActiveTrue(String purpose);
}
