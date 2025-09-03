package me.learn.now.service;

import me.learn.now.model.Topic;
import me.learn.now.model.Video;
import me.learn.now.model.Quiz;
import me.learn.now.repository.TopicRepo;
import me.learn.now.repository.VideoRepo;
import me.learn.now.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicService {
    @Autowired
    private TopicRepo tr;

    @Autowired
    private VideoRepo vr;

    @Autowired
    private QuizRepo qr;

    public Topic addTopic(Topic topic) {
        return tr.save(topic);
    }


    public List<Topic> getTopics() {
        return tr.findAll();
    }

    public Optional<Topic> getTopicById(Long id) {
        return tr.findById(id);
    }

    public Topic changeTopic(Long id, Topic topic) {
        Topic existingTopic = tr.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
        existingTopic.settName(topic.gettName());
        existingTopic.settDescription(topic.gettDescription());
        existingTopic.settLanguage(topic.gettLanguage());
        existingTopic.settPurpose(topic.gettPurpose());
        return tr.save(existingTopic);
    }


    public Optional<Topic> deleteTopic(Long id) {
        Optional<Topic> t = tr.findById(id);
        t.ifPresent(tr::delete);
        return t;
    }


    public List<Video> getVideoForTopic(Long tid) {
        // Ensure topic exists (optional but helpful)
        tr.findById(tid).orElseThrow(() -> new RuntimeException("Topic not found"));
        return vr.findByVTopic_TIdOrderByVPositionAsc(tid);
    }

    public List<Quiz> getQuizzesForTopic(Long tid) {
        tr.findById(tid).orElseThrow(() -> new RuntimeException("Topic not found"));
        return qr.findByQTopic_TId(tid);
    }

    @Transactional
    public List<Video> reorderVideos(Long topicId, List<Long> orderedVideoIds) {
        tr.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));

        List<Video> videos = vr.findByVTopic_TIdOrderByVPositionAsc(topicId);
        if (videos.isEmpty()) return videos;

        Map<Long, Video> byId = videos.stream().collect(Collectors.toMap(Video::getvId, v -> v));

        // Validate: all provided IDs belong to this topic
        for (Long vid : orderedVideoIds) {
            if (!byId.containsKey(vid)) {
                throw new RuntimeException("Video " + vid + " does not belong to Topic " + topicId);
            }
        }
        // Optional: ensure no missing videos if strict ordering required
        if (orderedVideoIds.size() != videos.size()) {
            throw new RuntimeException("Ordered list size (" + orderedVideoIds.size() + ") does not match topic video count (" + videos.size() + ")");
        }

        int pos = 1;
        for (Long vid : orderedVideoIds) {
            Video v = byId.get(vid);
            v.setvPosition(pos++);
        }
        return vr.saveAll(orderedVideoIds.stream().map(byId::get).collect(Collectors.toList()));
    }
}
