package me.learn.now.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uPid;

    @ManyToOne
    @JoinColumn(name = "uId", nullable = false)
    private User uPuser;

    @ManyToOne
    @JoinColumn(name = "tId")
    private Topic uPtopic;

    // CHANGED: Use enum for status and persist by name for clarity
    // @Enumerated(EnumType.STRING) → Store enum value as string (NOT_STARTED/IN_PROGRESS/COMPLETED)
    @Enumerated(EnumType.STRING)
    private ProgressStatus uPstatus; // e.g., NOT_STARTED, IN_PROGRESS, COMPLETED

    private LocalDateTime uPlastSeenAt;
    private Integer uPsecondsWatched;
    private LocalDateTime uPcreateAt;
    private LocalDateTime uPupdateAt;


    public Long getuPid() {
        return uPid;
    }

    public void setuPid(Long uPid) {
        this.uPid = uPid;
    }

    public User getuPuser() {
        return uPuser;
    }

    public void setuPuser(User uPuser) {
        this.uPuser = uPuser;
    }

    public Topic getuPtopic() {
        return uPtopic;
    }

    public void setuPtopic(Topic uPtopic) {
        this.uPtopic = uPtopic;
    }

    public ProgressStatus getuPstatus() {
        return uPstatus;
    }

    public void setuPstatus(ProgressStatus uPstatus) {
        this.uPstatus = uPstatus;
    }

    public LocalDateTime getuPlastSeenAt() {
        return uPlastSeenAt;
    }

    public void setuPlastSeenAt(LocalDateTime uPlastSeenAt) {
        this.uPlastSeenAt = uPlastSeenAt;
    }

    public Integer getuPsecondsWatched() {
        return uPsecondsWatched;
    }

    public void setuPsecondsWatched(Integer uPsecondsWatched) {
        this.uPsecondsWatched = uPsecondsWatched;
    }

    public LocalDateTime getuPcreateAt() {
        return uPcreateAt;
    }

    public void setuPcreateAt(LocalDateTime uPcreateAt) {
        this.uPcreateAt = uPcreateAt;
    }

    public LocalDateTime getuPupdateAt() {
        return uPupdateAt;
    }

    public void setuPupdateAt(LocalDateTime uPupdateAt) {
        this.uPupdateAt = uPupdateAt;
    }
}
