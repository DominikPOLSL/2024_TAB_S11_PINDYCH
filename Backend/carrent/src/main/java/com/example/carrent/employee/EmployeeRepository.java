package com.example.carrent.employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Employee entities.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Finds an employee by their login.
     * 
     * @param login the login of the employee to find.
     * @return an Optional containing the employee if found, or an empty Optional if
     *         not found.
     */
    Optional<Employee> findByLogin(String login);
}
