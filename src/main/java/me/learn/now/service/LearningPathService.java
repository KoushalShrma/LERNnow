package me.learn.now.service;

import me.learn.now.model.LearningPath;
import me.learn.now.model.Topic;
import me.learn.now.model.User;
import me.learn.now.repository.LearningPathRepo;
import me.learn.now.repository.TopicRepo;
import me.learn.now.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningPathService {

    @Autowired
    private LearningPathRepo learningPathRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TopicRepo topicRepo;

    // Hinglish: naya learning path create karne ke liye
    public LearningPath createLearningPath(LearningPath learningPath, Long userId) {
        // User ko fetch kar ke attach kar dete hai
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        learningPath.setUser(user);
        return learningPathRepo.save(learningPath);
    }

    // Hinglish: user ke saare learning paths get karne ke liye
    public List<LearningPath> getUserLearningPaths(Long userId) {
        return learningPathRepo.findByUserId(userId);
    }

    // Hinglish: specific learning path by ID
    public Optional<LearningPath> getLearningPathById(Long pathId) {
        return learningPathRepo.findById(pathId);
    }

    // Hinglish: public learning paths jo sabko dikhane hai
    public List<LearningPath> getPublicLearningPaths() {
        return learningPathRepo.findByIsPublicTrue();
    }

    // Hinglish: popular paths jo zyada topics ke saath hai
    public List<LearningPath> getPopularLearningPaths() {
        return learningPathRepo.findPopularPublicPaths();
    }

    // Hinglish: learning path me topic add karne ke liye
    public LearningPath addTopicToPath(Long pathId, Long topicId) {
        LearningPath path = learningPathRepo.findById(pathId)
                .orElseThrow(() -> new RuntimeException("Learning path not found"));

        Topic topic = topicRepo.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        // Hinglish: agar topic already hai toh add nahi karte duplicate
        if (!path.getTopics().contains(topic)) {
            path.getTopics().add(topic);
            return learningPathRepo.save(path);
        }

        return path;
    }

    // Hinglish: learning path se topic remove karne ke liye
    public LearningPath removeTopicFromPath(Long pathId, Long topicId) {
        LearningPath path = learningPathRepo.findById(pathId)
                .orElseThrow(() -> new RuntimeException("Learning path not found"));

        Topic topic = topicRepo.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        path.getTopics().remove(topic);
        return learningPathRepo.save(path);
    }

    // Hinglish: learning path update karne ke liye
    public LearningPath updateLearningPath(Long pathId, LearningPath updatedPath) {
        LearningPath existingPath = learningPathRepo.findById(pathId)
                .orElseThrow(() -> new RuntimeException("Learning path not found"));

        // Basic fields update kar dete hai
        existingPath.setName(updatedPath.getName());
        existingPath.setDescription(updatedPath.getDescription());
        existingPath.setPurpose(updatedPath.getPurpose());
        existingPath.setLanguage(updatedPath.getLanguage());
        existingPath.setLevel(updatedPath.getLevel());
        existingPath.setEstimatedHours(updatedPath.getEstimatedHours());
        existingPath.setIsPublic(updatedPath.getIsPublic());

        return learningPathRepo.save(existingPath);
    }

    // Hinglish: learning path delete karne ke liye
    public boolean deleteLearningPath(Long pathId) {
        if (learningPathRepo.existsById(pathId)) {
            learningPathRepo.deleteById(pathId);
            return true;
        }
        return false;
    }

    // Hinglish: purpose ke basis pe learning paths filter karne ke liye
    public List<LearningPath> getLearningPathsByPurpose(String purpose) {
        return learningPathRepo.findByPurpose(purpose);
    }

    // Hinglish: language ke basis pe learning paths filter karne ke liye
    public List<LearningPath> getLearningPathsByLanguage(String language) {
        return learningPathRepo.findByLanguage(language);
    }
}
