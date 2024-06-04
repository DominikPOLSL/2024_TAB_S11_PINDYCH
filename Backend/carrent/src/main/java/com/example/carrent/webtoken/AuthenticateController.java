package com.example.carrent.webtoken;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrent.admin.Admin;
import com.example.carrent.admin.AdminRepository;
import com.example.carrent.employee.Employee;
import com.example.carrent.employee.EmployeeRepository;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/authenticate")
    public Map<String, String> authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password()));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginForm.username());
            String token = jwtService.generateToken(userDetails);
            String id = "";

            Optional<Employee> employee = employeeRepository.findByLogin(loginForm.username());
            if (employee.isPresent()) {
                id = String.valueOf(employee.get().getId());

            }

            Optional<Admin> admin = adminRepository.findByLogin(loginForm.username());
            if (admin.isPresent()) {
                id = String.valueOf(admin.get().getId());

            }

            String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("UNKNOWN");

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("role", role);
            response.put("id", id);
            return response;
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }
}