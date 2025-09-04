package me.learn.now.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// CHANGED: Mark as JPA entity so it maps to a table
// @Entity → Marks ScoreCard as a persistent JPA entity
@Entity
public class ScoreCard {

    // CHANGED: Add primary key
    // @Id @GeneratedValue → Auto-generated primary key for ScoreCard
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CHANGED: Remove CascadeType.ALL to avoid cascading deletes to User
    // @OneToOne → One-to-One relation (ScoreCard ↔ User). ScoreCard owns the FK column uId
    // @JoinColumn(name = "uId", referencedColumnName = "uId") → FK in ScoreCard table referencing User.uId
    @OneToOne(optional = false)
    @JoinColumn(name="uId", referencedColumnName = "uId")
    private User user;

    private Double accuracy = 0.0; // Hinglish: default 0 se start karte hai
    private Double consistency = 0.0;
    private Double discipline = 0.0;
    private Double dedication = 0.0;
    private Integer streakDays = 0; // Hinglish: streak 0 se start
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        // Hinglish: naya scorecard create hone pe timestamp set kar dete hai
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        // Hinglish: scorecard update hone pe timestamp change kar dete hai
        updateAt = LocalDateTime.now();
    }

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

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getConsistency() {
        return consistency;
    }

    public void setConsistency(Double consistency) {
        this.consistency = consistency;
    }

    public Double getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Double discipline) {
        this.discipline = discipline;
    }

    public Double getDedication() {
        return dedication;
    }

    public void setDedication(Double dedication) {
        this.dedication = dedication;
    }

    public Integer getStreakDays() {
        return streakDays;
    }

    public void setStreakDays(Integer streakDays) {
        this.streakDays = streakDays;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
