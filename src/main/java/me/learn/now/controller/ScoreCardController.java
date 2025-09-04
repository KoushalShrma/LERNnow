package me.learn.now.controller;

import me.learn.now.model.ScoreCard;
import me.learn.now.service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // simple REST controller for scorecard
@RequestMapping("/api")
public class ScoreCardController {

    @Autowired
    private ScoreCardService scs; // service layer

    // Create new scorecard
    @PostMapping("/scorecards")
    public ResponseEntity<ScoreCard> add(@RequestBody ScoreCard sc){
        return ResponseEntity.ok(scs.add(sc));
    }

    // List all scorecards
    @GetMapping("/scorecards")
    public ResponseEntity<List<ScoreCard>> list(){
        return ResponseEntity.ok(scs.list());
    }

    // Get one by id
    @GetMapping("/scorecards/{id}")
    public ResponseEntity<ScoreCard> get(@PathVariable Long id){
        Optional<ScoreCard> sc = scs.get(id);
        return sc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get a user's scorecard
    @GetMapping("/users/{userId}/scorecard")
    public ResponseEntity<ScoreCard> getByUser(@PathVariable Long userId){
        Optional<ScoreCard> sc = scs.getByUser(userId);
        return sc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update scorecard
    @PutMapping("/scorecards/{id}")
    public ResponseEntity<ScoreCard> update(@PathVariable Long id, @RequestBody ScoreCard input){
        return ResponseEntity.ok(scs.update(id, input));
    }

    // Delete scorecard
    @DeleteMapping("/scorecards/{id}")
    public ResponseEntity<ScoreCard> delete(@PathVariable Long id){
        Optional<ScoreCard> sc = scs.delete(id);
        return sc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Hinglish: user ke liye scorecard generate ya refresh karne ke liye
    @PostMapping("/users/{userId}/scorecard/generate")
    public ResponseEntity<ScoreCard> generateScoreCard(@PathVariable Long userId){
        ScoreCard scoreCard = scs.generateOrUpdateScoreCard(userId);
        return ResponseEntity.ok(scoreCard);
    }

    // Hinglish: user ka performance summary get karne ke liye
    @GetMapping("/users/{userId}/performance-summary")
    public ResponseEntity<String> getPerformanceSummary(@PathVariable Long userId){
        String summary = scs.getPerformanceSummary(userId);
        return ResponseEntity.ok(summary);
    }
}
