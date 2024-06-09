package com.example.carrent.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling employee-related operations.
 */
@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Constructor injection for EmployeeService dependency.
     * 
     * @param employeeService the EmployeeService instance.
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for retrieving all employees.
     * 
     * @return a list of all employees.
     */
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Endpoint for adding a new employee.
     * 
     * @param emp the Employee object to be added.
     * @return a ResponseEntity containing the created employee and the HTTP status.
     */
    @PostMapping()
    public ResponseEntity<?> addNewEmployee(@RequestBody Employee emp) {
        Employee createdEmployee = employeeService.addNewEmployee(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    /**
     * Endpoint for retrieving an employee by ID.
     * 
     * @param id the ID of the employee to retrieve.
     * @return a ResponseEntity containing the retrieved employee and the HTTP
     *         status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
        Employee emp = employeeService.findById(id);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emp);
    }

    /**
     * Endpoint for deleting an employee by ID.
     * 
     * @param id the ID of the employee to delete.
     * @return an Optional containing the deleted employee if found, or an empty
     *         Optional if not found.
     */
    @DeleteMapping("/{id}")
    public Optional<Employee> deleteEmployee(@PathVariable("id") int id) {
        if (!employeeService.existById(id)) {
            // Employee not found
        }
        return employeeService.deleteEmployeeById(id);
    }

    /**
     * Endpoint for updating an existing employee.
     * 
     * @param id              the ID of the employee to update.
     * @param updatedEmployee the updated Employee object.
     * @return a ResponseEntity containing the updated employee and the HTTP status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employeeService.findById(id);

        existingEmployee.setId(updatedEmployee.getId());
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setSurname(updatedEmployee.getSurname());
        existingEmployee.setLogin(updatedEmployee.getLogin());
        existingEmployee.setPassword(updatedEmployee.getPassword());
        existingEmployee.setRoleType(updatedEmployee.getRoleType());

        Employee savedEmployee = employeeService.save(existingEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    /**
     * Endpoint for searching employees by attribute.
     * 
     * @param data the attribute data to search for.
     * @return an ArrayList of employees matching the attribute.
     */
    @GetMapping("/searchEmployee/{data}")
    public ArrayList<Employee> getEmployeesByAttribute(@PathVariable("data") String data) {
        return employeeService.findByAttribute(data);
    }
}
