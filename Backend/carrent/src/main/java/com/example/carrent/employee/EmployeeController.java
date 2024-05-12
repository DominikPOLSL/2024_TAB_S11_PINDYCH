package com.example.carrent.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();

    }

    @PostMapping()
    public ResponseEntity<?> addNewEmployee(@RequestBody Employee emp) {
        Employee createdEmployee = employeeService.addNewEmployee(emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
        Employee emp = employeeService.findById(id);
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) {
        if (!employeeService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        Employee emp = employeeService.findById(id);

        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(emp);
    }

}
