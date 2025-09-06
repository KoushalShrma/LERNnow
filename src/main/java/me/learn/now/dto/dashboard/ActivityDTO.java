package me.learn.now.dto.dashboard;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for user activity shown on the dashboard
 */
public class ActivityDTO {
    private Long id;
    private String type; // "quiz", "video", or "achievement"
    private String title;
    private Integer score; // Only for quiz activities
    private Integer progress; // Only for video activities (percentage)
    private LocalDateTime timestamp;
    private String date; // Formatted date for display (e.g., "2 hours ago")

    // Additional fields needed by DashboardService
    private Long topicId;
    private String topicName;
    private String detail;

    // Default constructor
    public ActivityDTO() {
    }

    // Constructor with required fields
    public ActivityDTO(Long id, String type, String title, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.timestamp = timestamp;
        this.date = formatTimestamp(timestamp);
    }

    // Format timestamp to human-readable string like "2 hours ago"
    private String formatTimestamp(LocalDateTime timestamp) {
        LocalDateTime now = LocalDateTime.now();
        long minutesDiff = java.time.Duration.between(timestamp, now).toMinutes();

        if (minutesDiff < 1) {
            return "just now";
        } else if (minutesDiff < 60) {
            return minutesDiff + " minute" + (minutesDiff == 1 ? "" : "s") + " ago";
        } else if (minutesDiff < 24 * 60) {
            long hours = minutesDiff / 60;
            return hours + " hour" + (hours == 1 ? "" : "s") + " ago";
        } else if (minutesDiff < 7 * 24 * 60) {
            long days = minutesDiff / (24 * 60);
            return days + " day" + (days == 1 ? "" : "s") + " ago";
        } else if (minutesDiff < 30 * 24 * 60) {
            long weeks = minutesDiff / (7 * 24 * 60);
            return weeks + " week" + (weeks == 1 ? "" : "s") + " ago";
        } else {
            long months = minutesDiff / (30 * 24 * 60);
            return months + " month" + (months == 1 ? "" : "s") + " ago";
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = (int) Math.round(score);
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.date = formatTimestamp(timestamp);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Additional getters and setters for fields used by DashboardService

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
