package com.example.carrent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.carrent.admin.Admin;
import com.example.carrent.admin.AdminDetailsService;
import com.example.carrent.admin.AdminRepository;

@RestController
public class RegistrationController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Autowired
    // private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/register/admin")
    public Admin createUser(@RequestBody Admin admin) {
        admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
        return adminRepository.save(admin);

    }
    // @PostMapping("/logout")
    // public ResponseEntity<?> logout() {
    // String authToken = request.getHeader("Authorization");// Pobierz token JWT z
    // żądania
    // tokenBlacklistService.blacklistToken(authToken);
    // SecurityContextHolder.clearContext(); // Wyczyść kontekst autoryzacji
    // return ResponseEntity.ok("Logged out successfully");
    // }

}
