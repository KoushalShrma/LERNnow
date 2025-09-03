package me.learn.now.service;

import me.learn.now.model.Quiz;
import me.learn.now.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo qr; // repo se DB baat karega

    public Quiz add(Quiz q){
        return qr.save(q);
    }

    public List<Quiz> list(){
        return qr.findAll();
    }

    public Optional<Quiz> get(Long id){
        return qr.findById(id);
    }

    public Quiz update(Long id, Quiz input){
        Quiz q = qr.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        q.setqSubTopic(input.getqSubTopic());
        q.setqDifficulty(input.getqDifficulty());
        q.setqLanguage(input.getqLanguage());
        q.setqPurpose(input.getqPurpose());
        q.setqIsActive(input.isqIsActive());
        q.setqTopic(input.getqTopic());
        return qr.save(q);
    }

    public Optional<Quiz> delete(Long id){
        Optional<Quiz> q = qr.findById(id);
        q.ifPresent(qr::delete);
        return q;
    }

    public Quiz setActive(Long id, boolean active){
        Quiz q = qr.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        q.setqIsActive(active);
        return qr.save(q);
    }
}
