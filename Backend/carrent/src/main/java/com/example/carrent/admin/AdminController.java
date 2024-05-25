package com.example.carrent.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    @PostMapping
    public ResponseEntity<?> addNewAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.addNewAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") int id) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") int id, @RequestBody Admin updatedAdmin) {
        Admin existingAdmin = adminService.findById(id);
        existingAdmin.setAdminId(updatedAdmin.getAdminId());
        existingAdmin.setAdminName(updatedAdmin.getAdminName());
        existingAdmin.setAdminSurname(updatedAdmin.getAdminSurname());
        existingAdmin.setAdminLogin(updatedAdmin.getAdminLogin());
        existingAdmin.setAdminPassword(updatedAdmin.getAdminPassword());
       

        Admin savedAdmin = adminService.save(existingAdmin);
        return ResponseEntity.ok(savedAdmin);
    }

    @GetMapping("/searchAdmin/{data}")
    public ArrayList<Admin> getAdminsByAttribute(@PathVariable("data") String data) {
        return adminService.findByAttribute(data);
    }
}
