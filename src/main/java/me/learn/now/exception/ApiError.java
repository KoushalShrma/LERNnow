package me.learn.now.exception;

import java.time.LocalDateTime;

// jab bhi error aaye, hum ye simple object bhejenge
public class ApiError {
    private LocalDateTime timestamp;
    private String path;
    private String code;
    private String message;
    private Object details; // optional extra info

    public ApiError() {}

    public ApiError(LocalDateTime timestamp, String path, String code, String message, Object details) {
        this.timestamp = timestamp;
        this.path = path;
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getDetails() { return details; }
    public void setDetails(Object details) { this.details = details; }
}

