package me.learn.now.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // DEV: Require Basic auth for API, permit others; disable CSRF for API testing
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").authenticated() // require auth for API
                        .anyRequest().permitAll() // allow non-API endpoints
                )
                .httpBasic(basic -> {}); // enable HTTP Basic authentication
        return http.build();
    }
}