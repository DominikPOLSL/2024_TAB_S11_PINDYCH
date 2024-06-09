package com.example.carrent.config;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller class for handling authentication-related operations.
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    /**
     * Logs out the currently authenticated user.
     * 
     * @param request the HTTP request containing the logout request
     * @return a response entity indicating the result of the logout operation
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String authToken = request.getHeader("Authorization");
            String token = authToken != null ? authToken.replace("Bearer ", "") : null;
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }
}
