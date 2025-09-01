package me.learn.now.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qId;
	private String qSubTopic;

	// CHANGED: Use a strong enum type with @Enumerated for safety and readability
	// @Enumerated(EnumType.STRING) → Persist enum by name (EASY/MEDIUM/HARD) instead of ordinal
	@Enumerated(EnumType.STRING)
	private Difficulty qDifficulty;

	private String qLanguage;
	private String qPurpose;
	private boolean qIsActive;
	private LocalDateTime qCreateAt;
	private LocalDateTime qUpdateAt;

	// No change: Quiz belongs to a Topic via FK tId on the owning side
	// @ManyToOne @JoinColumn(name = "tId") → Many-to-One relation (Quiz → Topic) using foreign key column tId
	@ManyToOne
	@JoinColumn(name = "tId")
	private Topic qTopic;


	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public String getqSubTopic() {
		return qSubTopic;
	}

	public void setqSubTopic(String qSubTopic) {
		this.qSubTopic = qSubTopic;
	}

	public Difficulty getqDifficulty() {
		return qDifficulty;
	}

	public void setqDifficulty(Difficulty qDifficulty) {
		this.qDifficulty = qDifficulty;
	}

	public String getqLanguage() {
		return qLanguage;
	}

	public void setqLanguage(String qLanguage) {
		this.qLanguage = qLanguage;
	}

	public String getqPurpose() {
		return qPurpose;
	}

	public void setqPurpose(String qPurpose) {
		this.qPurpose = qPurpose;
	}

	public boolean isqIsActive() {
		return qIsActive;
	}

	public void setqIsActive(boolean qIsActive) {
		this.qIsActive = qIsActive;
	}

	public LocalDateTime getqCreateAt() {
		return qCreateAt;
	}

	public void setqCreateAt(LocalDateTime qCreateAt) {
		this.qCreateAt = qCreateAt;
	}

	public LocalDateTime getqUpdateAt() {
		return qUpdateAt;
	}

	public void setqUpdateAt(LocalDateTime qUpdateAt) {
		this.qUpdateAt = qUpdateAt;
	}

	public Topic getqTopic() {
		return qTopic;
	}

	public void setqTopic(Topic qTopic) {
		this.qTopic = qTopic;
	}
}
