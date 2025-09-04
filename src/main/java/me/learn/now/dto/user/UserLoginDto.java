package me.learn.now.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String uEmail;

    @NotBlank(message = "Password is required")
    private String uPass;

    // Constructors
    public UserLoginDto() {}

    public UserLoginDto(String uEmail, String uPass) {
        this.uEmail = uEmail;
        this.uPass = uPass;
    }

    // Getters and Setters
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
}
