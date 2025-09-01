package me.learn.now.repository;

import me.learn.now.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

// CHANGED: Extend JpaRepository to get CRUD methods for Video
// extends JpaRepository<Video, Long> → Spring Data repository with Long primary key
public interface VideoRepo extends JpaRepository<Video, Long> {

}
