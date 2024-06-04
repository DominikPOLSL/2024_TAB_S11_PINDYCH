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
    public Optional<Employee> deleteEmployee(@PathVariable("id") int id) {
        if (!employeeService.existById(id)) {
            // return ResponseEntity.notFound().build();
        }
        Employee emp = employeeService.findById(id);

        return employeeService.deleteEmployeeById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee updatedEmployee) {

        Employee existingEmployee = employeeService.findById(id);

        existingEmployee.setId(updatedEmployee.getId());
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setSurname(updatedEmployee.getSurname());
        existingEmployee.setLogin(updatedEmployee.getLogin());
        existingEmployee.setPassword(updatedEmployee.getPassword());
        existingEmployee.setRoleType(updatedEmployee.getRoleType());

        Employee saveEmployee = employeeService.save(existingEmployee);
        return ResponseEntity.ok(saveEmployee);
    }

    @GetMapping("/searchEmployee/{data}")
    public ArrayList<Employee> getEmployeesByAttribute(@PathVariable("data") String data) {
        return employeeService.findByAttribute(data);
    }

}
