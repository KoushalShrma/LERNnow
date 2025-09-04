package me.learn.now.controller;

import me.learn.now.model.LearningPath;
import me.learn.now.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/learning-paths")
public class LearningPathController {

    @Autowired
    private LearningPathService learningPathService;

    // Hinglish: naya learning path create karne ke liye
    @PostMapping("/user/{userId}")
    public ResponseEntity<LearningPath> createLearningPath(
            @PathVariable Long userId,
            @RequestBody LearningPath learningPath) {

        LearningPath createdPath = learningPathService.createLearningPath(learningPath, userId);
        return ResponseEntity.ok(createdPath);
    }

    // Hinglish: user ke saare learning paths get karne ke liye
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningPath>> getUserLearningPaths(@PathVariable Long userId) {
        List<LearningPath> paths = learningPathService.getUserLearningPaths(userId);
        return ResponseEntity.ok(paths);
    }

    // Hinglish: specific learning path by ID
    @GetMapping("/{pathId}")
    public ResponseEntity<LearningPath> getLearningPath(@PathVariable Long pathId) {
        Optional<LearningPath> path = learningPathService.getLearningPathById(pathId);
        return path.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Hinglish: sabke liye public learning paths
    @GetMapping("/public")
    public ResponseEntity<List<LearningPath>> getPublicLearningPaths() {
        List<LearningPath> publicPaths = learningPathService.getPublicLearningPaths();
        return ResponseEntity.ok(publicPaths);
    }

    // Hinglish: popular learning paths jo zyada topics ke saath hai
    @GetMapping("/popular")
    public ResponseEntity<List<LearningPath>> getPopularLearningPaths() {
        List<LearningPath> popularPaths = learningPathService.getPopularLearningPaths();
        return ResponseEntity.ok(popularPaths);
    }

    // Hinglish: learning path me topic add karne ke liye
    @PostMapping("/{pathId}/topics/{topicId}")
    public ResponseEntity<LearningPath> addTopicToPath(
            @PathVariable Long pathId,
            @PathVariable Long topicId) {

        LearningPath updatedPath = learningPathService.addTopicToPath(pathId, topicId);
        return ResponseEntity.ok(updatedPath);
    }

    // Hinglish: learning path se topic remove karne ke liye
    @DeleteMapping("/{pathId}/topics/{topicId}")
    public ResponseEntity<LearningPath> removeTopicFromPath(
            @PathVariable Long pathId,
            @PathVariable Long topicId) {

        LearningPath updatedPath = learningPathService.removeTopicFromPath(pathId, topicId);
        return ResponseEntity.ok(updatedPath);
    }

    // Hinglish: learning path update karne ke liye
    @PutMapping("/{pathId}")
    public ResponseEntity<LearningPath> updateLearningPath(
            @PathVariable Long pathId,
            @RequestBody LearningPath learningPath) {

        LearningPath updatedPath = learningPathService.updateLearningPath(pathId, learningPath);
        return ResponseEntity.ok(updatedPath);
    }

    // Hinglish: learning path delete karne ke liye
    @DeleteMapping("/{pathId}")
    public ResponseEntity<Void> deleteLearningPath(@PathVariable Long pathId) {
        boolean deleted = learningPathService.deleteLearningPath(pathId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Hinglish: purpose ke basis pe filter karne ke liye
    @GetMapping("/filter/purpose/{purpose}")
    public ResponseEntity<List<LearningPath>> getLearningPathsByPurpose(@PathVariable String purpose) {
        List<LearningPath> paths = learningPathService.getLearningPathsByPurpose(purpose);
        return ResponseEntity.ok(paths);
    }

    // Hinglish: language ke basis pe filter karne ke liye
    @GetMapping("/filter/language/{language}")
    public ResponseEntity<List<LearningPath>> getLearningPathsByLanguage(@PathVariable String language) {
        List<LearningPath> paths = learningPathService.getLearningPathsByLanguage(language);
        return ResponseEntity.ok(paths);
    }
}
