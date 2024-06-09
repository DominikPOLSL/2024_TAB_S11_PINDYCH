package com.example.carrent.employee;

//import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for handling employee-related business logic.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieves all employees.
     * 
     * @return a list of all employees.
     */
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Adds a new employee.
     * 
     * @param employee the employee object to be added.
     * @return the added employee.
     */
    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Checks if an employee exists by ID.
     * 
     * @param id the ID of the employee to check.
     * @return true if the employee exists, false otherwise.
     */
    public boolean existById(int id) {
        return employeeRepository.existsById(id);
    }

    /**
     * Deletes an employee by ID.
     * 
     * @param id the ID of the employee to delete.
     * @return an Optional containing the deleted employee if found, or an empty
     *         Optional if not found.
     */
    public Optional<Employee> deleteEmployeeById(int id) {
        Optional<Employee> e = employeeRepository.findById(id);
        employeeRepository.deleteById(id);
        return e;
    }

    /**
     * Finds an employee by ID.
     * 
     * @param id the ID of the employee to find.
     * @return the found employee, or null if not found.
     */
    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing employee.
     * 
     * @param existingEmployee the existing employee to update.
     * @return the updated employee.
     */
    public Employee save(Employee existingEmployee) {
        return employeeRepository.save(existingEmployee);
    }

    /**
     * Finds employees by attribute data.
     * 
     * @param data the attribute data to search for.
     * @return an ArrayList of employees matching the attribute.
     */
    public ArrayList<Employee> findByAttribute(String data) {
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee e : employeeRepository.findAll()) {
            if (e.getName().contains(data)) {
                list.add(e);
            }
            if (e.getSurname().contains(data)) {
                list.add(e);
            }
            if (e.getRoleType().contains(data)) {
                list.add(e);
            }
            String employeeIdString = String.valueOf(e.getId());
            if (employeeIdString.contains(data)) {
                list.add(e);
            }
        }
        ArrayList<Employee> uniqueList = new ArrayList<>();
        for (Employee e : list) {
            if (!uniqueList.contains(e)) {
                uniqueList.add(e);
            }
        }
        return uniqueList;
    }
}
