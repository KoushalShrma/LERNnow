package me.learn.now.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vId;
	private String vYoutubeId;
	private String vTitle;
	private String vChannel;
	private Integer vDuration; // in seconds
	private String vLanguage;
	private Integer vPosition; // position in the topic
	private String vChaptersJson; // JSON representation of chapters
	private LocalDateTime vCreateAt;
	private LocalDateTime vUpdateAt;

	// No change: Video is the owning side with FK tId
	// @ManyToOne @JoinColumn(name = "tId") → Many-to-One relation (Video → Topic) using foreign key column tId
	@ManyToOne
	@JoinColumn(name = "tId")
	private Topic vTopic;

	// CHANGED: Remove CascadeType.ALL to avoid deleting Quiz when Video is deleted; keep association optional
	// @OneToOne(optional = true) → One-to-One relation (Video ↔ Quiz), quiz is optional and not cascaded
	@OneToOne(optional = true)
	@JoinColumn(name = "qId", referencedColumnName = "qId")
	private Quiz vQuiz;


	public Long getvId() {
		return vId;
	}

	public void setvId(Long vId) {
		this.vId = vId;
	}

	public String getvYoutubeId() {
		return vYoutubeId;
	}

	public void setvYoutubeId(String vYoutubeId) {
		this.vYoutubeId = vYoutubeId;
	}

	public String getvTitle() {
		return vTitle;
	}

	public void setvTitle(String vTitle) {
		this.vTitle = vTitle;
	}

	public String getvChannel() {
		return vChannel;
	}

	public void setvChannel(String vChannel) {
		this.vChannel = vChannel;
	}

	public Integer getvDuration() {
		return vDuration;
	}

	public void setvDuration(Integer vDuration) {
		this.vDuration = vDuration;
	}

	public String getvLanguage() {
		return vLanguage;
	}

	public void setvLanguage(String vLanguage) {
		this.vLanguage = vLanguage;
	}

	public Integer getvPosition() {
		return vPosition;
	}

	public void setvPosition(Integer vPosition) {
		this.vPosition = vPosition;
	}

	public String getvChaptersJson() {
		return vChaptersJson;
	}

	public void setvChaptersJson(String vChaptersJson) {
		this.vChaptersJson = vChaptersJson;
	}

	public LocalDateTime getvCreateAt() {
		return vCreateAt;
	}

	public void setvCreateAt(LocalDateTime vCreateAt) {
		this.vCreateAt = vCreateAt;
	}

	public LocalDateTime getvUpdateAt() {
		return vUpdateAt;
	}

	public void setvUpdateAt(LocalDateTime vUpdateAt) {
		this.vUpdateAt = vUpdateAt;
	}

	public Topic getvTopic() {
		return vTopic;
	}

	public void setvTopic(Topic vTopic) {
		this.vTopic = vTopic;
	}

	public Quiz getvQuiz() {
		return vQuiz;
	}

	public void setvQuiz(Quiz vQuiz) {
		this.vQuiz = vQuiz;
	}
}
