package com.example.employeeManagement.Repository;


import com.example.employeeManagement.Modal.UserReg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserReg, String> {

    UserReg findByUsername(String username);
}
