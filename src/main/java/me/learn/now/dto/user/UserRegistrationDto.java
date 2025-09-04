package me.learn.now.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String uName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String uEmail;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String uPass;

    private String uPreferredLanguage = "en"; // Hinglish: default English

    // Constructors
    public UserRegistrationDto() {}

    public UserRegistrationDto(String uName, String uEmail, String uPass, String uPreferredLanguage) {
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPass = uPass;
        this.uPreferredLanguage = uPreferredLanguage;
    }

    // Getters and Setters
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

    public String getuPreferredLanguage() {
        return uPreferredLanguage;
    }

    public void setuPreferredLanguage(String uPreferredLanguage) {
        this.uPreferredLanguage = uPreferredLanguage;
    }
}
