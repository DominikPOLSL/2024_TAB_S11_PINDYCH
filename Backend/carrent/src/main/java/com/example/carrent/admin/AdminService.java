package com.example.carrent.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing admin-related operations.
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Retrieves a list of all admins.
     * 
     * @return a list of all admins
     */
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Adds a new admin.
     * 
     * @param admin the admin to add
     * @return the added admin
     */
    public Admin addNewAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    /**
     * Checks if an admin exists by their ID.
     * 
     * @param id the ID to check
     * @return true if an admin exists with the given ID, false otherwise
     */
    public boolean existById(int id) {
        return adminRepository.existsById(id);
    }

    /**
     * Deletes an admin by their ID.
     * 
     * @param id the ID of the admin to delete
     * @return an optional containing the deleted admin, or empty if no admin was
     *         found
     */
    public Optional<Admin> deleteAdminById(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        adminRepository.deleteById(id);
        return admin;
    }

    /**
     * Finds an admin by their ID.
     * 
     * @param id the ID of the admin to find
     * @return the found admin, or null if no admin was found
     */
    public Admin findById(int id) {
        return adminRepository.findById(id).orElse(null);
    }

    /**
     * Saves an existing admin.
     * 
     * @param existingAdmin the admin to save
     * @return the saved admin
     */
    public Admin save(Admin existingAdmin) {
        return adminRepository.save(existingAdmin);
    }

    /**
     * Finds admins by a specific attribute.
     * 
     * @param data the attribute to search by
     * @return a list of admins matching the attribute
     */
    public ArrayList<Admin> findByAttribute(String data) {
        ArrayList<Admin> list = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            if (admin.getName().contains(data) || admin.getSurname().contains(data) ||
                    String.valueOf(admin.getId()).contains(data)) {
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
