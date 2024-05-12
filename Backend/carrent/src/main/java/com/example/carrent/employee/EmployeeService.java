package com.example.carrent.employee;

//import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public  Optional<Employee> deleteEmployeeById(int id) {
        Optional<Employee> e = employeeRepository.findById(id);
        employeeRepository.deleteById(id);
        return e;
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee save(Employee existingEmployee) {
        return employeeRepository.save(existingEmployee);
    }
}
