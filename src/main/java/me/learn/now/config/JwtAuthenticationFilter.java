package me.learn.now.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.learn.now.service.JwtService;
import me.learn.now.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Hinglish: request header se Authorization nikaalni hai
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Hinglish: agar Authorization header nahi hai ya Bearer se start nahi hota toh filter skip kar do
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Hinglish: "Bearer " ke baad jo token hai usse extract karte hai
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // Hinglish: agar email mil gaya aur user abhi authenticated nahi hai
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Hinglish: database se user details laate hai
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Hinglish: token valid hai ya nahi check karte hai
            if (jwtService.validateToken(jwt, userDetails)) {
                // Hinglish: valid token hai toh authentication object banate hai
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Hinglish: Security context me authentication set kar dete hai
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
