package me.learn.now.dto.dashboard;

/**
 * Data Transfer Object for course recommendations shown on the dashboard
 */
public class RecommendationDTO {
    private Long id;
    private String type; // "continue", "new", or "challenge"
    private String title;
    private String description;
    private String timeLeft; // Only for "continue" type
    private String duration; // Only for "new" type
    private String reward; // Only for "challenge" type
    private String icon; // "book", "target", or "award"

    // Additional fields needed by DashboardService
    private Long topicId;
    private String topicName;
    private String topicDescription;
    private int progress;
    private String reason;

    // Default constructor
    public RecommendationDTO() {
    }

    // Constructor with required fields
    public RecommendationDTO(Long id, String type, String title, String description) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;

        // Set default icon based on type
        if ("continue".equals(type)) {
            this.icon = "book";
        } else if ("new".equals(type)) {
            this.icon = "target";
        } else if ("challenge".equals(type)) {
            this.icon = "award";
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

        // Update icon when type changes
        if ("continue".equals(type)) {
            this.icon = "book";
        } else if ("new".equals(type)) {
            this.icon = "target";
        } else if ("challenge".equals(type)) {
            this.icon = "award";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
