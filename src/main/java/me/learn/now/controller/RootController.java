package me.learn.now.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "LearNnow Backend API is running! 🚀\n" +
               "Frontend: http://localhost:3000\n" +
               "API Docs: http://localhost:8080/swagger-ui.html\n" +
               "Auth Endpoints: /api/auth/login, /api/auth/register";
    }

    @GetMapping("/health")
    public String health() {
        return "OK - Backend is healthy! ✅";
    }
}
