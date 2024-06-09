package com.example.carrent.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.carrent.employee.Employee;
import com.example.carrent.employee.EmployeeRepository;
import com.example.carrent.vehiclecargiver.VehicleCarGiver;
import com.example.carrent.vehiclecargiver.VehicleCarGiverRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller for managing admin-related operations.
 */
@RestController
@RequestMapping(path = "api/admin")
public class AdminController {

    private final AdminService adminService;
    private final EmployeeRepository employeeRepository;
    private final VehicleCarGiverRepository vehicleCarGiverRepository;

    /**
     * Constructs an AdminController with the specified service and repositories.
     * 
     * @param adminService              the admin service
     * @param employeeRepository        the employee repository
     * @param vehicleCarGiverRepository the vehicle caregiver repository
     */
    @Autowired
    public AdminController(AdminService adminService, EmployeeRepository employeeRepository,
            VehicleCarGiverRepository vehicleCarGiverRepository) {
        this.adminService = adminService;
        this.employeeRepository = employeeRepository;
        this.vehicleCarGiverRepository = vehicleCarGiverRepository;
    }

    /**
     * Retrieves a list of all admins.
     * 
     * @return a list of all admins
     */
    @GetMapping
    public List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    /**
     * Adds a new admin.
     * 
     * @param admin the admin to add
     * @return the response entity with the created admin
     */
    @PostMapping
    public ResponseEntity<?> addNewAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.addNewAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    /**
     * Retrieves an admin by their ID.
     * 
     * @param id the ID of the admin to retrieve
     * @return the response entity with the found admin or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable("id") int id) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }

    /**
     * Updates an existing admin.
     * 
     * @param id           the ID of the admin to update
     * @param updatedAdmin the updated admin data
     * @return the response entity with the updated admin
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable("id") int id, @RequestBody Admin updatedAdmin) {
        Admin existingAdmin = adminService.findById(id);
        existingAdmin.setId(updatedAdmin.getId());
        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setSurname(updatedAdmin.getSurname());
        existingAdmin.setLogin(updatedAdmin.getLogin());
        existingAdmin.setPassword(updatedAdmin.getPassword());

        Admin savedAdmin = adminService.save(existingAdmin);
        return ResponseEntity.ok(savedAdmin);
    }

    /**
     * Searches for admins by a specific attribute.
     * 
     * @param data the attribute to search by
     * @return a list of admins matching the attribute
     */
    @GetMapping("/searchAdmin/{data}")
    public ArrayList<Admin> getAdminsByAttribute(@PathVariable("data") String data) {
        return adminService.findByAttribute(data);
    }

    /**
     * Sets a new role for an employee by transferring them to VehicleCarGiver.
     * 
     * @param id the ID of the employee to transfer
     */
    @PostMapping("/setNewRole/{id}")
    public void setNewRole(@PathVariable("id") int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        VehicleCarGiver carGiver = new VehicleCarGiver();
        carGiver.setName(employee.getName());
        carGiver.setSurname(employee.getSurname());

        vehicleCarGiverRepository.save(carGiver);

        // else throw new RuntimeException("Error while transferring employee to
        // VehicleCarGiver");
    }

}
