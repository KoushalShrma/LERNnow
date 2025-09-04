package me.learn.now.repository;

import me.learn.now.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// CHANGED: Extend JpaRepository to get CRUD methods for Video
// extends JpaRepository<Video, Long> → Spring Data repository with Long primary key
public interface VideoRepo extends JpaRepository<Video, Long> {
    // Hinglish: topic ke videos position ke order me fetch karne ke liye - updated field names
    List<Video> findByTopicIdOrderByPositionAsc(Long topicId);
}
