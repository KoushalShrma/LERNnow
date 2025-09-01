package me.learn.now.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uId;

	@Column(unique = true, nullable = false)
	private String uName;

	@Column(unique = true, nullable = false)
	private String uEmail;

	@Column(nullable = false)
	private String uPass;

	// CHANGED: Fix mapping - use collection and correct mappedBy value
	// @OneToMany(mappedBy = "uPuser") → One-to-Many relation (User → UserProgress) inverse side; FK owned by UserProgress.uPuser
	@OneToMany(mappedBy = "uPuser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserProgress> uUprogress = new ArrayList<>();

	private String role;
	private String uPreferredLanguage;
	private LocalDateTime uCreateAt;
	private LocalDateTime uUpdateAt;


	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public String getuPass() {
		return uPass;
	}

	public void setuPass(String uPass) {
		this.uPass = uPass;
	}

	public List<UserProgress> getuUprogress() {
		return uUprogress;
	}

	public void setuUprogress(List<UserProgress> uUprogress) {
		this.uUprogress = uUprogress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getuPreferredLanguage() {
		return uPreferredLanguage;
	}

	public void setuPreferredLanguage(String uPreferredLanguage) {
		this.uPreferredLanguage = uPreferredLanguage;
	}

	public LocalDateTime getuCreateAt() {
		return uCreateAt;
	}

	public void setuCreateAt(LocalDateTime uCreateAt) {
		this.uCreateAt = uCreateAt;
	}

	public LocalDateTime getuUpdateAt() {
		return uUpdateAt;
	}

	public void setuUpdateAt(LocalDateTime uUpdateAt) {
		this.uUpdateAt = uUpdateAt;
	}
}
