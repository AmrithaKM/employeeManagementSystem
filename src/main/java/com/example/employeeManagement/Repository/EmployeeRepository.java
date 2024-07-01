package com.example.employeeManagement.Repository;

import com.example.employeeManagement.Modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmployeeId(String employeeId);
}
