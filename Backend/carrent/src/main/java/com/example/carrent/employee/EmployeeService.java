package com.example.carrent.employee;

//import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ArrayList<Employee> findByAttribute(String data) {
        ArrayList<Employee> list = new ArrayList<>();
        for(Employee e : employeeRepository.findAll()) {

            if(e.getEmployeeName().contains(data)) {
                list.add(e);
            }

            if(e.getEmployeeSurname().contains(data)) {
                list.add(e);
            }

            if(e.getRoleType().contains(data)) {
                list.add(e);
            }

            String employeeIdString = String.valueOf(e.getEmployeeId());
            if(employeeIdString.contains(data)) {
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
