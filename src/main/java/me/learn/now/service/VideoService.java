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
        v.setTitle(input.getTitle());
        v.setChannel(input.getChannel());
        v.setYoutubeId(input.getYoutubeId());
        v.setDuration(input.getDuration());
        v.setLanguage(input.getLanguage());
        v.setPosition(input.getPosition());
        v.setChaptersJson(input.getChaptersJson());
        v.setTopic(input.getTopic());
        v.setQuiz(input.getQuiz());
        return vr.save(v);
    }

    // Delete
    public Optional<Video> delete(Long id){
        Optional<Video> v = vr.findById(id);
        v.ifPresent(vr::delete);
        return v;
    }
}
