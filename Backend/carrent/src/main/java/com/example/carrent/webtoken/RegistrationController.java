package com.example.carrent.webtoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrent.admin.Admin;
import com.example.carrent.admin.AdminRepository;
import com.example.carrent.employee.Employee;
import com.example.carrent.employee.EmployeeRepository;

/**
 * Controller for handling user registration and providing authentication
 * tokens.
 */
@RestController
public class RegistrationController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * Registers a new admin user and returns an authentication token.
     *
     * @param admin the admin user to register
     * @return the authentication token for the registered admin user
     */
    @PostMapping("/register/admin")
    public String registerAdminAndGetToken(@RequestBody Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(admin.getLogin());
        return jwtService.generateToken(userDetails);
    }

    /**
     * Registers a new employee user and returns an authentication token.
     *
     * @param employee the employee user to register
     * @return the authentication token for the registered employee user
     */
    @PostMapping("/register/employee")
    public String registerEmployeeAndGetToken(@RequestBody Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(employee.getLogin());
        return jwtService.generateToken(userDetails);
    }

}
