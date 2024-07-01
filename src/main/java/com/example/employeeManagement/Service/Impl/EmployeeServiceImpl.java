package com.example.employeeManagement.Service.Impl;

import com.example.employeeManagement.DTO.AddressDTO;
import com.example.employeeManagement.DTO.EmployeeDTO;
import com.example.employeeManagement.Modal.Address;
import com.example.employeeManagement.Modal.Employee;
import com.example.employeeManagement.Repository.EmployeeRepository;
import com.example.employeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {

        if (employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId()).isPresent()) {
            throw new RuntimeException("Employee with id " + employeeDTO.getEmployeeId() + " already exists.");
        }
        Employee emp = new Employee();
        emp.setEmployeeId(employeeDTO.getEmployeeId());
        emp.setName(employeeDTO.getName());
        emp.setDepartment(employeeDTO.getDepartment());
        emp.setEmailId(employeeDTO.getEmailId());
        emp.setJobTitle(employeeDTO.getJobTitle());

        AddressDTO addressDTO = employeeDTO.getAddress();
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setPostalCode(addressDTO.getPostalCode());
        emp.setAddress(address);


        Employee saveEmployee = employeeRepository.save(emp);


    }

    @Override
    public void updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeExists = employeeRepository.findByEmployeeId(employeeId);
        if (employeeExists.isPresent()) {
            Employee emp = employeeExists.get();


            if (!emp.getEmployeeId().equals(employeeDTO.getEmployeeId()) && employeeRepository.findByEmployeeId(employeeDTO.getEmployeeId()).isPresent()) {
                throw new RuntimeException("Employee with id " + employeeDTO.getEmployeeId() + " already exists.");
            }


            emp.setEmployeeId(employeeDTO.getEmployeeId());
            emp.setName(employeeDTO.getName());
            emp.setDepartment(employeeDTO.getDepartment());
            emp.setEmailId(employeeDTO.getEmailId());
            emp.setJobTitle(employeeDTO.getJobTitle());

            AddressDTO addressDTO = employeeDTO.getAddress();
            System.out.println(addressDTO);
            Address address = emp.getAddress();
            if (address == null) {
                address = new Address();
            }
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setStreet(addressDTO.getStreet());
            address.setPostalCode(addressDTO.getPostalCode());
            emp.setAddress(address);
            employeeRepository.save(emp);
        } else {
            throw new RuntimeException("employee is not found");
        }


    }


    @Override
    public void deleteEmployee(String employeeId) {
        Optional<Employee> employeeExists = employeeRepository.findByEmployeeId(employeeId);
        if (employeeExists.isPresent()) {
            Employee emp = employeeExists.get();
            employeeRepository.delete(emp);
        } else {
            throw new RuntimeException("employee not exists");
        }
    }




    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map((employee) -> this.convertToDto(employee))
                .collect(Collectors.toList());
    }


    private EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setName(employee.getName());
        dto.setDepartment(employee.getDepartment());
        dto.setJobTitle(employee.getJobTitle());
        AddressDTO addressDTO = new AddressDTO();
        Address address = employee.getAddress();
        if (address != null) {
            addressDTO.setStreet(address.getStreet());
            addressDTO.setCity(address.getCity());
            addressDTO.setState(address.getState());
            addressDTO.setPostalCode(address.getPostalCode());
        }
        dto.setAddress(addressDTO);
        dto.setEmailId(employee.getEmailId());
        return dto;
    }
}
