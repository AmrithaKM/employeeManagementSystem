package com.example.employeeManagement.Service;

import com.example.employeeManagement.DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeService {


     public void saveEmployee(EmployeeDTO employeeDTO);
     public void updateEmployee(String employeeId, EmployeeDTO employeeDTO);

     public void deleteEmployee(String employeeId);

     List<EmployeeDTO> getAllEmployees();
}
