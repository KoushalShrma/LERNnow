package me.learn.now.dto.user;

public class UserResponseDto {

    private Long uId;
    private String uName;
    private String uEmail;
    private String role;
    private String uPreferredLanguage;
    private String uCreateAt;

    // Constructors
    public UserResponseDto() {}

    public UserResponseDto(Long uId, String uName, String uEmail, String role, String uPreferredLanguage, String uCreateAt) {
        this.uId = uId;
        this.uName = uName;
        this.uEmail = uEmail;
        this.role = role;
        this.uPreferredLanguage = uPreferredLanguage;
        this.uCreateAt = uCreateAt;
    }

    // Getters and Setters
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

    public String getuCreateAt() {
        return uCreateAt;
    }

    public void setuCreateAt(String uCreateAt) {
        this.uCreateAt = uCreateAt;
    }
}
