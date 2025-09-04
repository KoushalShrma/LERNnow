package me.learn.now.controller;

import me.learn.now.dto.user.UserLoginRequestDto;
import me.learn.now.dto.user.UserLoginResponseDto;
import me.learn.now.dto.user.UserRegisterRequestDto;
import me.learn.now.model.User;
import me.learn.now.repository.UserRepo;
import me.learn.now.service.JwtService;
import me.learn.now.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDto registerDto) {
        try {
            // Hinglish: pehle check karte hai ki user already exist toh nahi karta
            if (userRepo.existsByEmail(registerDto.getEmail())) {
                return ResponseEntity.badRequest().body("Email already registered!");
            }

            if (userRepo.existsByName(registerDto.getName())) {
                return ResponseEntity.badRequest().body("Username already taken!");
            }

            // Hinglish: naya user object banate hai
            User newUser = new User();
            newUser.setName(registerDto.getName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPassword(registerDto.getPassword()); // Service me hash ho jayega
            newUser.setPreferredLanguage(registerDto.getPreferredLanguage() != null ?
                registerDto.getPreferredLanguage() : "en");

            // Hinglish: user ko database me save karte hai
            User savedUser = userService.addUser(newUser);

            return ResponseEntity.ok("User registered successfully! ID: " + savedUser.getId());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequestDto loginDto) {
        try {
            // Hinglish: Spring Security se authentication karte hai
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
                )
            );

            // Hinglish: agar authentication successful hai toh user details laate hai
            User user = userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

            // Hinglish: JWT token generate karte hai - ab username string se direct generate kar sakte hai
            String token = jwtService.generateToken(user.getEmail());

            // Hinglish: response DTO me token aur user details pack kar dete hai
            UserLoginResponseDto response = new UserLoginResponseDto(
                token,
                user.getId(),
                user.getEmail(),
                user.getName()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: Invalid credentials");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        try {
            // Hinglish: JWT token se current user ki details nikaalte hai
            String email = authentication.getName();
            User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

            // Hinglish: password remove kar ke user details return karte hai
            user.setPassword(null);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to get user details: " + e.getMessage());
        }
    }
}
