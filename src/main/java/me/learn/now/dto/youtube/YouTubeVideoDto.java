package me.learn.now.dto.youtube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hinglish: YouTube se aane wali video details ko store karne ke liye DTO
 * Simple POJO class hai jo video ki basic information rakhti hai
 */
@Data
public class YouTubeVideoDto {

    private String videoId;        // YouTube video ka unique ID
    private String title;          // Video ka title
    private String description;    // Video ka description
    private String channelTitle;   // Channel ka naam
    private String thumbnailUrl;   // Video ka thumbnail image URL
    private String videoUrl;       // Complete YouTube video URL
    private String publishedAt;    // Kab publish hui thi video
    private Long duration;         // Video ki length seconds mein
    private Long viewCount;        // Kitne views hai
    private String category;       // Video category (Education, Technology etc)

    // Manual constructors to work without Lombok processing
    public YouTubeVideoDto() {
        // Default no-argument constructor
    }

    public YouTubeVideoDto(String videoId, String title, String description,
                          String channelTitle, String thumbnailUrl, String videoUrl,
                          String publishedAt, Long duration, Long viewCount, String category) {
        // All-args constructor
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.channelTitle = channelTitle;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = videoUrl;
        this.publishedAt = publishedAt;
        this.duration = duration;
        this.viewCount = viewCount;
        this.category = category;
    }

    // Hinglish: Constructor jo sirf basic details leta hai
    public YouTubeVideoDto(String videoId, String title, String description,
                          String channelTitle, String thumbnailUrl) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.channelTitle = channelTitle;
        this.thumbnailUrl = thumbnailUrl;
        this.videoUrl = "https://www.youtube.com/watch?v=" + videoId;
    }

    // Manual getters and setters to work without Lombok processing
    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getChannelTitle() { return channelTitle; }
    public void setChannelTitle(String channelTitle) { this.channelTitle = channelTitle; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }

    public Long getDuration() { return duration; }
    public void setDuration(Long duration) { this.duration = duration; }

    public Long getViewCount() { return viewCount; }
    public void setViewCount(Long viewCount) { this.viewCount = viewCount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
