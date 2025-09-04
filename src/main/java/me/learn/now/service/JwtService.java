package me.learn.now.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Hinglish: JWT ke liye secret key - production me environment variable use karna
    @Value("${jwt.secret:mySecretKeyForLEARNnowProjectWhichShouldBeLongEnoughForSecurity}")
    private String secret;

    @Value("${jwt.expiration:86400000}") // 24 hours in milliseconds
    private int jwtExpiration;

    // Hinglish: JWT token se username extract karne ke liye
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Hinglish: JWT token se expiration date nikaalne ke liye
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Hinglish: JWT token se specific claim extract karne ke liye
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Hinglish: JWT token se saare claims nikaalne ke liye
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token: " + e.getMessage());
        }
    }

    // Hinglish: check karna ki token expire toh nahi ho gaya
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Hinglish: UserDetails ke saath token generate karne ke liye
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // Hinglish: username string ke saath directly token generate karne ke liye (AuthController ke liye)
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Hinglish: custom claims ke saath token create karne ke liye
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return createToken(extraClaims, userDetails.getUsername());
    }

    // Hinglish: actual token creation logic
    private String createToken(Map<String, Object> claims, String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty");
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Hinglish: token validate karne ke liye - username match aur expired nahi hona chahiye
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    // Hinglish: signing key generate karne ke liye - UTF-8 encoding use kar rahe hai
    private SecretKey getSignKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Hinglish: sirf token valid hai ya nahi check karne ke liye (without user details)
    public Boolean isTokenValid(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return false;
            }
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
