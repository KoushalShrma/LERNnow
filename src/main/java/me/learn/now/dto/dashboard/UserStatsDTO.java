package me.learn.now.dto.dashboard;

/**
 * Data Transfer Object for user statistics shown on the dashboard
 */
public class UserStatsDTO {
    private int totalTopics;
    private int completedTopics;
    private int totalQuizzes;
    private double averageScore;
    private int streakDays;
    private int totalXP;
    private long totalStudyTimeMinutes;
    private String favoriteTopic;

    // Default constructor
    public UserStatsDTO() {
    }

    // Getters and setters
    public int getTotalTopics() {
        return totalTopics;
    }

    public void setTotalTopics(int totalTopics) {
        this.totalTopics = totalTopics;
    }

    public int getCompletedTopics() {
        return completedTopics;
    }

    public void setCompletedTopics(int completedTopics) {
        this.completedTopics = completedTopics;
    }

    public int getTotalQuizzes() {
        return totalQuizzes;
    }

    public void setTotalQuizzes(int totalQuizzes) {
        this.totalQuizzes = totalQuizzes;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getStreakDays() {
        return streakDays;
    }

    public void setStreakDays(int streakDays) {
        this.streakDays = streakDays;
    }

    public int getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(int totalXP) {
        this.totalXP = totalXP;
    }

    public long getTotalStudyTimeMinutes() {
        return totalStudyTimeMinutes;
    }

    public void setTotalStudyTimeMinutes(long totalStudyTimeMinutes) {
        this.totalStudyTimeMinutes = totalStudyTimeMinutes;
    }

    public String getFavoriteTopic() {
        return favoriteTopic;
    }

    public void setFavoriteTopic(String favoriteTopic) {
        this.favoriteTopic = favoriteTopic;
    }
}
