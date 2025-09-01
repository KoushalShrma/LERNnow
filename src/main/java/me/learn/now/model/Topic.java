package me.learn.now.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Topic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tId;
	private String tName;
	private String tDescription;
	private String tPurpose;
	private String tLanguage;
	private String tLevel;
	private LocalDateTime tCreateAt;
	private LocalDateTime tUpdateAt;

	// CHANGED: Use mappedBy instead of @JoinColumn on OneToMany; FK lives on Video table as tId
	// @OneToMany(mappedBy = "vTopic") → One-to-Many relation (Topic → Videos) using the owning side on Video
	@OneToMany(mappedBy = "vTopic", cascade = CascadeType.ALL, orphanRemoval = true) // mappedBy fix + cascade cleanup
	private List<Video> tVideos = new ArrayList<>(); // initialize to avoid NPEs

	// No change to mapping name, just clarifying it's inverse side
	// @OneToMany(mappedBy = "qTopic") → One-to-Many relation (Topic → Quizzes) inverse side; Quiz owns FK
	@OneToMany(mappedBy = "qTopic")
	private List<Quiz> tQuizzes = new ArrayList<>();


	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String gettDescription() {
		return tDescription;
	}

	public void settDescription(String tDescription) {
		this.tDescription = tDescription;
	}

	public String gettPurpose() {
		return tPurpose;
	}

	public void settPurpose(String tPurpose) {
		this.tPurpose = tPurpose;
	}

	public String gettLanguage() {
		return tLanguage;
	}

	public void settLanguage(String tLanguage) {
		this.tLanguage = tLanguage;
	}

	public String gettLevel() {
		return tLevel;
	}

	public void settLevel(String tLevel) {
		this.tLevel = tLevel;
	}

	public LocalDateTime gettCreateAt() {
		return tCreateAt;
	}

	public void settCreateAt(LocalDateTime tCreateAt) {
		this.tCreateAt = tCreateAt;
	}

	public LocalDateTime gettUpdateAt() {
		return tUpdateAt;
	}

	public void settUpdateAt(LocalDateTime tUpdateAt) {
		this.tUpdateAt = tUpdateAt;
	}

	public List<Video> gettVideos() {
		return tVideos;
	}

	public void settVideos(List<Video> tVideos) {
		this.tVideos = tVideos;
	}

	public List<Quiz> gettQuizzes() {
		return tQuizzes;
	}

	public void settQuizzes(List<Quiz> tQuizzes) {
		this.tQuizzes = tQuizzes;
	}
}
