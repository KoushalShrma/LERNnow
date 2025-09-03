package me.learn.now.controller;

import me.learn.now.model.Quiz;
import me.learn.now.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService qs; // yaha se service methods call karenge

    @PostMapping
    public ResponseEntity<Quiz> add(@RequestBody Quiz q){
        return ResponseEntity.ok(qs.add(q));
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> list(){
        return ResponseEntity.ok(qs.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> get(@PathVariable Long id){
        Optional<Quiz> q = qs.get(id);
        return q.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> update(@PathVariable Long id, @RequestBody Quiz input){
        return ResponseEntity.ok(qs.update(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quiz> delete(@PathVariable Long id){
        Optional<Quiz> q = qs.delete(id);
        return q.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Active flag toggle (true/false)
    @PatchMapping("/{id}/active")
    public ResponseEntity<Quiz> setActive(@PathVariable Long id, @RequestParam boolean active){
        return ResponseEntity.ok(qs.setActive(id, active));
    }
}
