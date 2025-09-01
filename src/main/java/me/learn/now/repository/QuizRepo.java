package me.learn.now.repository;

import me.learn.now.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz,Long>{

}
