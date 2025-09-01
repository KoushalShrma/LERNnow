package me.learn.now.repository;

import me.learn.now.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

// CHANGED: Extend JpaRepository to get CRUD methods for Topic
// extends JpaRepository<Topic, Long> → Spring Data repository with Long primary key
public interface TopicRepo extends JpaRepository<Topic, Long> {

}
