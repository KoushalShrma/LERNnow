package me.learn.now.controller;

import me.learn.now.dto.youtube.YouTubeVideoDto;
import me.learn.now.service.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Hinglish: YouTube API ke through learning videos search karne ke liye controller
 * Yaha hum students ko YouTube se educational content provide karte hai
 */
@RestController
@RequestMapping("/api/youtube")
@CrossOrigin(origins = "*") // Frontend ke liye CORS enable kar diya
public class YouTubeController {

    @Autowired
    private YouTubeService youTubeService;

    /**
     * Hinglish: Keyword ke basis pe YouTube se videos search karte hai
     * Example: /api/youtube/search?query=java&max=5
     */
    @GetMapping("/search")
    public ResponseEntity<List<YouTubeVideoDto>> searchVideos(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int max) {

        try {
            // Hinglish: YouTube service se videos fetch karte hai
            List<YouTubeVideoDto> videos = youTubeService.searchEducationalVideos(query, max);
            return ResponseEntity.ok(videos);
        } catch (Exception e) {
            // Hinglish: error handle karte hai agar YouTube API fail ho jaye
            throw new RuntimeException("YouTube videos fetch karne mein problem: " + e.getMessage());
        }
    }

    /**
     * Hinglish: Popular programming channels se trending videos laate hai
     * Example: /api/youtube/popular?max=8
     */
    @GetMapping("/popular")
    public ResponseEntity<List<YouTubeVideoDto>> getPopularVideos(
            @RequestParam(defaultValue = "8") int max) {

        try {
            List<YouTubeVideoDto> videos = youTubeService.getPopularProgrammingVideos(max);
            return ResponseEntity.ok(videos);
        } catch (Exception e) {
            throw new RuntimeException("Popular videos fetch karne mein problem: " + e.getMessage());
        }
    }

    /**
     * Hinglish: Specific topic ke liye curated videos
     * Example: /api/youtube/topic/java?max=6
     */
    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<YouTubeVideoDto>> getVideosByTopic(
            @PathVariable String topic,
            @RequestParam(defaultValue = "6") int max) {

        try {
            List<YouTubeVideoDto> videos = youTubeService.getVideosByTopic(topic, max);
            return ResponseEntity.ok(videos);
        } catch (Exception e) {
            throw new RuntimeException("Topic videos fetch karne mein problem: " + e.getMessage());
        }
    }

    /**
     * Hinglish: YouTube API health check - API working hai ya nahi
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        try {
            // Hinglish: simple test search kar ke API health check karte hai
            List<YouTubeVideoDto> testVideos = youTubeService.searchEducationalVideos("programming", 1);
            if (testVideos != null && !testVideos.isEmpty()) {
                return ResponseEntity.ok("YouTube API working perfectly! ✅");
            } else {
                return ResponseEntity.ok("YouTube API connected but no results 🔍");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("YouTube API mein problem hai: " + e.getMessage());
        }
    }
}
