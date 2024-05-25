package com.example.carrent.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            //return ResponseEntity.notFound().build();
        }
        Employee emp = employeeService.findById(id);

        return employeeService.deleteEmployeeById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee updatedEmployee) {

        Employee existingEmployee = employeeService.findById(id);

        existingEmployee.setEmployeeId(updatedEmployee.getEmployeeId());
        existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
        existingEmployee.setEmployeeSurname(updatedEmployee.getEmployeeSurname());
        existingEmployee.setEmployeeLogin(updatedEmployee.getEmployeeLogin());
        existingEmployee.setEmployeePassword(updatedEmployee.getEmployeePassword());
        existingEmployee.setRoleType(updatedEmployee.getRoleType());


        Employee saveEmployee = employeeService.save(existingEmployee);
        return ResponseEntity.ok(saveEmployee);
    }
    @GetMapping("/searchEmployee/{data}")
    public ArrayList<Employee>  getEmployeesByAttribute(@PathVariable("data") String data) {
        return employeeService.findByAttribute(data);
    }
}
