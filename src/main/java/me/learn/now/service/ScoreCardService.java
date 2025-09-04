package me.learn.now.service;

import me.learn.now.model.ScoreCard;
import me.learn.now.model.User;
import me.learn.now.model.UserProgress;
import me.learn.now.repository.ScoreCardRepo;
import me.learn.now.repository.UserRepo;
import me.learn.now.repository.UserProgressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreCardService {

    @Autowired
    private ScoreCardRepo sr; // repo jo DB ke saath baat karta hai

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserProgressRepo userProgressRepo;

    // Create
    public ScoreCard add(ScoreCard sc){
        return sr.save(sc);
    }

    // Read all
    public List<ScoreCard> list(){
        return sr.findAll();
    }

    // Read one
    public Optional<ScoreCard> get(Long id){
        return sr.findById(id);
    }

    // Read by user
    public Optional<ScoreCard> getByUser(Long userId){
        return sr.findByUserId(userId);
    }

    // Update
    public ScoreCard update(Long id, ScoreCard input){
        ScoreCard sc = sr.findById(id).orElseThrow(() -> new RuntimeException("ScoreCard not found"));
        // sirf tracked fields update kar rahe
        sc.setAccuracy(input.getAccuracy());
        sc.setConsistency(input.getConsistency());
        sc.setDiscipline(input.getDiscipline());
        sc.setDedication(input.getDedication());
        sc.setStreakDays(input.getStreakDays());
        return sr.save(sc);
    }

    // Delete
    public Optional<ScoreCard> delete(Long id){
        Optional<ScoreCard> sc = sr.findById(id);
        sc.ifPresent(sr::delete);
        return sc;
    }

    // Hinglish: user ke liye scorecard generate ya update karne ke liye
    public ScoreCard generateOrUpdateScoreCard(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Hinglish: existing scorecard check karte hai
        Optional<ScoreCard> existingScoreCard = sr.findByUserId(userId);

        ScoreCard scoreCard;
        if (existingScoreCard.isPresent()) {
            scoreCard = existingScoreCard.get();
        } else {
            // Hinglish: naya scorecard banate hai
            scoreCard = new ScoreCard();
            scoreCard.setUser(user);
        }

        // Hinglish: user progress se statistics calculate karte hai
        calculateAndUpdateScores(scoreCard, userId);

        return sr.save(scoreCard);
    }

    // Hinglish: user progress ke basis pe scores calculate karne ke liye
    private void calculateAndUpdateScores(ScoreCard scoreCard, Long userId) {
        List<UserProgress> userProgressList = userProgressRepo.findByUserId(userId);

        if (userProgressList.isEmpty()) {
            // Hinglish: agar koi progress nahi hai toh default values set kar dete hai
            scoreCard.setAccuracy(0.0);
            scoreCard.setConsistency(0.0);
            scoreCard.setDiscipline(0.0);
            scoreCard.setDedication(0.0);
            scoreCard.setStreakDays(0);
            return;
        }

        // Hinglish: accuracy calculate karte hai - completed topics / total topics
        long completedTopics = userProgressList.stream()
            .mapToLong(up -> "COMPLETED".equals(up.getStatus().toString()) ? 1 : 0)
            .sum();
        double accuracy = (double) completedTopics / userProgressList.size() * 100;

        // Hinglish: consistency calculate karte hai - regular activity ke basis pe
        double consistency = calculateConsistency(userProgressList);

        // Hinglish: discipline calculate karte hai - completion rate ke basis pe
        double discipline = calculateDiscipline(userProgressList);

        // Hinglish: dedication calculate karte hai - total time spent ke basis pe
        double dedication = calculateDedication(userProgressList);

        // Hinglish: streak days calculate karte hai - consecutive days activity
        int streakDays = calculateStreakDays(userProgressList);

        // Hinglish: scorecard update kar dete hai
        scoreCard.setAccuracy(Math.round(accuracy * 100.0) / 100.0);
        scoreCard.setConsistency(Math.round(consistency * 100.0) / 100.0);
        scoreCard.setDiscipline(Math.round(discipline * 100.0) / 100.0);
        scoreCard.setDedication(Math.round(dedication * 100.0) / 100.0);
        scoreCard.setStreakDays(streakDays);
    }

    // Hinglish: consistency calculate karne ke liye - last seen activity frequency
    private double calculateConsistency(List<UserProgress> progressList) {
        if (progressList.isEmpty()) return 0.0;

        long recentActivityCount = progressList.stream()
            .filter(up -> up.getLastSeenAt() != null)
            .filter(up -> ChronoUnit.DAYS.between(up.getLastSeenAt(), LocalDateTime.now()) <= 7)
            .count();

        return (double) recentActivityCount / progressList.size() * 100;
    }

    // Hinglish: discipline calculate karne ke liye - completion rate
    private double calculateDiscipline(List<UserProgress> progressList) {
        if (progressList.isEmpty()) return 0.0;

        long inProgressOrCompleted = progressList.stream()
            .filter(up -> !"NOT_STARTED".equals(up.getStatus().toString()))
            .count();

        return (double) inProgressOrCompleted / progressList.size() * 100;
    }

    // Hinglish: dedication calculate karne ke liye - time spent basis pe
    private double calculateDedication(List<UserProgress> progressList) {
        if (progressList.isEmpty()) return 0.0;

        int totalSecondsWatched = progressList.stream()
            .filter(up -> up.getSecondsWatched() != null)
            .mapToInt(up -> up.getSecondsWatched())
            .sum();

        // Hinglish: hours me convert kar ke percentage return karte hai (max 100)
        double hoursWatched = totalSecondsWatched / 3600.0;
        return Math.min(hoursWatched * 10, 100); // 10 hours = 100% dedication
    }

    // Hinglish: consecutive streak days calculate karne ke liye
    private int calculateStreakDays(List<UserProgress> progressList) {
        // Hinglish: simple implementation - recent activity ke basis pe streak count
        long recentActivities = progressList.stream()
            .filter(up -> up.getLastSeenAt() != null)
            .filter(up -> ChronoUnit.DAYS.between(up.getLastSeenAt(), LocalDateTime.now()) <= 1)
            .count();

        return (int) recentActivities; // simplified streak calculation
    }

    // Hinglish: user ke overall performance summary get karne ke liye
    public String getPerformanceSummary(Long userId) {
        Optional<ScoreCard> scoreCardOpt = getByUser(userId);

        if (scoreCardOpt.isEmpty()) {
            return "No scorecard available. Start learning to generate your scorecard!";
        }

        ScoreCard scoreCard = scoreCardOpt.get();
        double avgScore = (scoreCard.getAccuracy() + scoreCard.getConsistency() +
                          scoreCard.getDiscipline() + scoreCard.getDedication()) / 4;

        if (avgScore >= 80) {
            return "Excellent! You're doing great! Keep it up! 🌟";
        } else if (avgScore >= 60) {
            return "Good progress! Try to be more consistent! 👍";
        } else if (avgScore >= 40) {
            return "You're getting started! Focus on building a routine! 💪";
        } else {
            return "Time to get serious about learning! Start small and be consistent! 🚀";
        }
    }
}
