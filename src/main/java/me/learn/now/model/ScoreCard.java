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
    private Long sId;

    // CHANGED: Remove CascadeType.ALL to avoid cascading deletes to User
    // @OneToOne → One-to-One relation (ScoreCard ↔ User). ScoreCard owns the FK column uId
    // @JoinColumn(name = "uId", referencedColumnName = "uId") → FK in ScoreCard table referencing User.uId
    @OneToOne(optional = false)
    @JoinColumn(name="uId", referencedColumnName = "uId")
    private User sUser;

    private Double sAccuracy;
    private Double sConsistency;
    private Double sDiscipline;
    private Double sDedication;
    private Integer sStreakDays;
    private LocalDateTime sUpdateAt;


    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public User getsUser() {
        return sUser;
    }

    public void setsUser(User sUser) {
        this.sUser = sUser;
    }

    public Double getsAccuracy() {
        return sAccuracy;
    }

    public void setsAccuracy(Double sAccuracy) {
        this.sAccuracy = sAccuracy;
    }

    public Double getsConsistency() {
        return sConsistency;
    }

    public void setsConsistency(Double sConsistency) {
        this.sConsistency = sConsistency;
    }

    public Double getsDiscipline() {
        return sDiscipline;
    }

    public void setsDiscipline(Double sDiscipline) {
        this.sDiscipline = sDiscipline;
    }

    public Double getsDedication() {
        return sDedication;
    }

    public void setsDedication(Double sDedication) {
        this.sDedication = sDedication;
    }

    public Integer getsStreakDays() {
        return sStreakDays;
    }

    public void setsStreakDays(Integer sStreakDays) {
        this.sStreakDays = sStreakDays;
    }

    public LocalDateTime getsUpdateAt() {
        return sUpdateAt;
    }

    public void setsUpdateAt(LocalDateTime sUpdateAt) {
        this.sUpdateAt = sUpdateAt;
    }
}
