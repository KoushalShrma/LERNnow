package me.learn.now.service;

import me.learn.now.model.ProgressStatus;
import me.learn.now.model.Topic;
import me.learn.now.model.User;
import me.learn.now.model.UserProgress;
import me.learn.now.repository.TopicRepo;
import me.learn.now.repository.UserProgressRepo;
import me.learn.now.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserProgressService {
    @Autowired
    private UserProgressRepo upr; // progress table pe CRUD
    @Autowired
    private UserRepo ur;         // user ko attach karne ke liye
    @Autowired
    private TopicRepo tr;        // topic ko attach/check karne ke liye

    // List: ek user ka saara progress
    public List<UserProgress> listByUser(Long userId){
        return upr.findByUPuser_UId(userId);
    }

    // Get: ensure ye progress isi user ka hai
    public Optional<UserProgress> getForUser(Long userId, Long progressId){
        Optional<UserProgress> up = upr.findById(progressId);
        return up.filter(p -> p.getuPuser() != null && p.getuPuser().getuId().equals(userId));
    }

    // Create: user attach + optional topic attach
    public UserProgress create(Long userId, UserProgress input){
        User u = ur.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        input.setuPuser(u);
        if (input.getuPtopic() != null && input.getuPtopic().gettId() != null){
            Topic t = tr.findById(input.getuPtopic().gettId()).orElseThrow(() -> new NoSuchElementException("Topic not found"));
            input.setuPtopic(t);
        }
        if (input.getuPcreateAt() == null) input.setuPcreateAt(LocalDateTime.now());
        input.setuPupdateAt(LocalDateTime.now());
        return upr.save(input);
    }

    // Patch update: status/seconds/lastSeenAt
    public UserProgress patch(Long userId, Long progressId, UserProgress delta){
        UserProgress p = upr.findById(progressId).orElseThrow(() -> new NoSuchElementException("Progress not found"));
        if (p.getuPuser() == null || !p.getuPuser().getuId().equals(userId))
            throw new NoSuchElementException("Progress doesn't belong to user");

        if (delta.getuPstatus() != null) p.setuPstatus(delta.getuPstatus());
        if (delta.getuPsecondsWatched() != null) p.setuPsecondsWatched(delta.getuPsecondsWatched());
        if (delta.getuPlastSeenAt() != null) p.setuPlastSeenAt(delta.getuPlastSeenAt());
        p.setuPupdateAt(LocalDateTime.now());
        return upr.save(p);
    }

    // Delete
    public Optional<UserProgress> delete(Long userId, Long progressId){
        Optional<UserProgress> p = upr.findById(progressId)
                .filter(x -> x.getuPuser() != null && x.getuPuser().getuId().equals(userId));
        p.ifPresent(upr::delete);
        return p;
    }

    // Helper: get by topic
    public Optional<UserProgress> getByTopic(Long userId, Long topicId){
        return upr.findByUPuser_UIdAndUPtopic_TId(userId, topicId);
    }

    // Helper: update only status by topic
    public UserProgress setStatusByTopic(Long userId, Long topicId, ProgressStatus status){
        UserProgress p = upr.findByUPuser_UIdAndUPtopic_TId(userId, topicId)
                .orElseThrow(() -> new NoSuchElementException("Progress not found for topic"));
        p.setuPstatus(status);
        p.setuPupdateAt(LocalDateTime.now());
        return upr.save(p);
    }

    // Helper: update only watch seconds by topic
    public UserProgress setWatchSecondsByTopic(Long userId, Long topicId, Integer seconds){
        UserProgress p = upr.findByUPuser_UIdAndUPtopic_TId(userId, topicId)
                .orElseThrow(() -> new NoSuchElementException("Progress not found for topic"));
        p.setuPsecondsWatched(seconds);
        p.setuPupdateAt(LocalDateTime.now());
        return upr.save(p);
    }
}
