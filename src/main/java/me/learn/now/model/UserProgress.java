package me.learn.now.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tId")
    private Topic topic;

    // CHANGED: Use enum for status and persist by name for clarity
    // @Enumerated(EnumType.STRING) → Store enum value as string (NOT_STARTED/IN_PROGRESS/COMPLETED)
    @Enumerated(EnumType.STRING)
    private ProgressStatus status; // e.g., NOT_STARTED, IN_PROGRESS, COMPLETED

    private LocalDateTime lastSeenAt;
    private Integer secondsWatched;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // Added field for progress percentage
    private int progressPercentage;

    @PrePersist
    protected void onCreate() {
        // Hinglish: naya progress entry create hone pe timestamps set kar dete hai
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
        // Hinglish: default status NOT_STARTED rakh dete hai agar kuch nahi hai
        if (status == null) {
            status = ProgressStatus.NOT_STARTED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        // Hinglish: progress update hone pe timestamp aur lastSeenAt update kar dete hai
        updateAt = LocalDateTime.now();
        if (status == ProgressStatus.IN_PROGRESS) {
            lastSeenAt = LocalDateTime.now();
        }
    }

    // Add getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public ProgressStatus getStatus() {
        return status;
    }

    public void setStatus(ProgressStatus status) {
        this.status = status;
    }

    public LocalDateTime getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(LocalDateTime lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }

    public Integer getSecondsWatched() {
        return secondsWatched;
    }

    public void setSecondsWatched(Integer secondsWatched) {
        this.secondsWatched = secondsWatched;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    // Add missing methods required by DashboardService

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Long getTopicId() {
        return topic != null ? topic.getId() : null;
    }

    public long getStudyTimeSeconds() {
        return secondsWatched != null ? secondsWatched : 0;
    }
}
