package me.learn.now.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice // yaha pe globally errors ko handle kar rahe
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> build(HttpStatus status, String code, String message, Object details, String path){
        ApiError err = new ApiError(LocalDateTime.now(), path, code, message, details);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNotFound(NoSuchElementException ex, HttpServletRequest req){
        return build(HttpStatus.NOT_FOUND, "NOT_FOUND", ex.getMessage(), null, req.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpServletRequest req){
        return build(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage(), null, req.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req){
        return build(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED", "Invalid input", ex.getBindingResult().getAllErrors(), req.getRequestURI());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex, HttpServletRequest req){
        // Hinglish: agar koi aur error aaya jo humne catch nahi kiya, toh 400 de dete (dev friendly)
        return build(HttpStatus.BAD_REQUEST, "RUNTIME_ERROR", ex.getMessage(), null, req.getRequestURI());
    }
}

