package com.example.carrent.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public Admin addNewAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public boolean existById(int id) {
        return adminRepository.existsById(id);
    }

    public Optional<Admin> deleteAdminById(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        adminRepository.deleteById(id);
        return admin;
    }

    public Admin findById(int id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin save(Admin existingAdmin) {
        return adminRepository.save(existingAdmin);
    }

    public ArrayList<Admin> findByAttribute(String data) {
        ArrayList<Admin> list = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            if (admin.getAdminName().contains(data) || admin.getAdminSurname().contains(data) ||
                String.valueOf(admin.getAdminId()).contains(data)) {
                list.add(admin);
            }
        }
        ArrayList<Admin> uniqueList = new ArrayList<>();
        for (Admin admin : list) {
            if (!uniqueList.contains(admin)) {
                uniqueList.add(admin);
            }
        }
        return uniqueList;
    }
}
