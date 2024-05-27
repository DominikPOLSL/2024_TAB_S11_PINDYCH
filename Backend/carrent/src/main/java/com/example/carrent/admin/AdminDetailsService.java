package com.example.carrent.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository; // Przyjmując, że masz repozytorium dla administratorów

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByAdminLogin(username);
        
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }
        var adminObj = admin.get();    
        return org.springframework.security.core.userdetails.User
                .withUsername(adminObj.getAdminLogin())
                .password(adminObj.getAdminPassword())
                .roles("ADMIN") // Przyjmując, że rola administratora jest "ADMIN"
                .build();
    }

    // private String[] getRoles(Admin admin){
    //     if(admin.getRole() == null){
    //         return new String[]{"ADMIN"};
    //     }
    //     return admin.getRole().split(",");
    // }


}