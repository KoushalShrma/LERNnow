package me.learn.now.dto.user;

public class UserLoginRequestDto {
    private String email;
    private String password;

    // Hinglish: login ke liye sirf email aur password chahiye
    public UserLoginRequestDto() {}

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
