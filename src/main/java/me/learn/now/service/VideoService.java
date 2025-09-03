package me.learn.now.service;

import me.learn.now.model.Video;
import me.learn.now.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepo vr; // yahi se DB calls jayengi

    // Create
    public Video add(Video v){
        return vr.save(v);
    }

    // Read one
    public Optional<Video> get(Long id){
        return vr.findById(id);
    }

    // Read all
    public List<Video> list(){
        return vr.findAll();
    }

    // Update
    public Video update(Long id, Video input){
        Video v = vr.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        // jo fields zaroori lagti hain woh update kar rahe
        v.setvTitle(input.getvTitle());
        v.setvChannel(input.getvChannel());
        v.setvYoutubeId(input.getvYoutubeId());
        v.setvDuration(input.getvDuration());
        v.setvLanguage(input.getvLanguage());
        v.setvPosition(input.getvPosition());
        v.setvChaptersJson(input.getvChaptersJson());
        v.setvTopic(input.getvTopic());
        v.setvQuiz(input.getvQuiz());
        return vr.save(v);
    }

    // Delete
    public Optional<Video> delete(Long id){
        Optional<Video> v = vr.findById(id);
        v.ifPresent(vr::delete);
        return v;
    }
}
