package me.learn.now.repository;

import me.learn.now.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz,Long>{
    // Fetch quizzes for a topic
    List<Quiz> findByQTopic_TId(Long tId);
}
