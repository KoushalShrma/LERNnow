package me.learn.now.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    // Hinglish: learning path ka purpose - JOB_INTERVIEW, UPSKILL, EXAM etc
    private String purpose;

    private String language;
    private String level; // BEGINNER, INTERMEDIATE, ADVANCED

    // Hinglish: kis user ne ye path banaya hai
    @ManyToOne
    @JoinColumn(name = "uId", nullable = false)
    private User user;

    // Hinglish: learning path me kaun kaun se topics hai
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "learning_path_topics",
        joinColumns = @JoinColumn(name = "lpId"),
        inverseJoinColumns = @JoinColumn(name = "tId")
    )
    private List<Topic> topics = new ArrayList<>();

    private Integer estimatedHours;
    private Boolean isPublic = false; // Hinglish: default private rakhte hai
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        // Hinglish: naya learning path create hone pe timestamp set kar dete hai
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        // Hinglish: update pe sirf update timestamp change karte hai
        updateAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
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
}
