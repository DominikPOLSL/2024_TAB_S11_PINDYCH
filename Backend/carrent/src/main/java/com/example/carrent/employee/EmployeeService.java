package com.example.carrent.employee;

//import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();

    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean existById(int id) {
        return employeeRepository.existsById(id);
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee save(Employee existingEmployee) {
        return employeeRepository.save(existingEmployee);
    }
}
