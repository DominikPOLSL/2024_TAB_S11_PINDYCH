package com.example.carrent.webtoken;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.carrent.admin.Admin;
import com.example.carrent.admin.AdminRepository;
import com.example.carrent.employee.Employee;
import com.example.carrent.employee.EmployeeRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<Admin> admin = adminRepository.findByAdminLogin(username);
        
        if (admin.isPresent()){
            var adminObj = admin.get();    
            return User.builder()
                    .username(adminObj.getAdminLogin())
                    .password(adminObj.getAdminPassword())
                    .roles("ADMIN") 
                    .build();
        }else {
            Optional<Employee> employee = employeeRepository.findByEmployeeLogin(username);
            if (employee.isPresent()){
            var employeeObj = employee.get();    
            return User.builder()
                    .username(employeeObj.getEmployeeLogin())
                    .password(employeeObj.getEmployeePassword())
                    .roles("EMPLOYEE") 
                    .build();
            } else
            throw new UsernameNotFoundException("User not found with username: " + username);

        }
    }


}