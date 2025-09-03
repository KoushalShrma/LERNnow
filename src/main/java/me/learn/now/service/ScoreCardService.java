package me.learn.now.service;

import me.learn.now.model.ScoreCard;
import me.learn.now.repository.ScoreCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreCardService {

    @Autowired
    private ScoreCardRepo sr; // repo jo DB ke saath baat karta hai

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
        return sr.findBySUser_UId(userId);
    }

    // Update
    public ScoreCard update(Long id, ScoreCard input){
        ScoreCard sc = sr.findById(id).orElseThrow(() -> new RuntimeException("ScoreCard not found"));
        // sirf tracked fields update kar rahe
        sc.setsAccuracy(input.getsAccuracy());
        sc.setsConsistency(input.getsConsistency());
        sc.setsDiscipline(input.getsDiscipline());
        sc.setsDedication(input.getsDedication());
        sc.setsStreakDays(input.getsStreakDays());
        sc.setsUpdateAt(input.getsUpdateAt());
        return sr.save(sc);
    }

    // Delete
    public Optional<ScoreCard> delete(Long id){
        Optional<ScoreCard> sc = sr.findById(id);
        sc.ifPresent(sr::delete);
        return sc;
    }
}
