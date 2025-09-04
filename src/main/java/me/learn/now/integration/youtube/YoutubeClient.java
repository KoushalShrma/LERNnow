package me.learn.now.integration.youtube;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;

// Hinglish: Ye bas ek dummy client hai abhi ke liye.
// Jab YouTube Data API key mil jaaye, yahi pe HTTP calls add karenge.
@Component
public class YoutubeClient {

    // Search videos by keyword + language (stub)
    public List<Map<String, Object>> searchVideos(String query, String language, int max){
        // Abhi ke liye empty result de rhe, baad me API call se fill hoga
        return List.of();
    }

    // Get video details (title/channel/duration) by IDs (stub)
    public Map<String, Object> getVideoDetails(String youtubeId){
        return Map.of(
                "youtubeId", youtubeId,
                "title", "TBD - from YouTube API",
                "channel", "TBD",
                "durationSec", Duration.ofMinutes(10).toSeconds()
        );
    }
}

