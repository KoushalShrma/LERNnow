package me.learn.now.dto.youtube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hinglish: YouTube se aane wali video details ko store karne ke liye DTO
 * Simple POJO class hai jo video ki basic information rakhti hai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
