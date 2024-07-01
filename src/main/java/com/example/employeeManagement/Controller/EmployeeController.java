package com.example.employeeManagement.Controller;

import com.example.employeeManagement.DTO.EmployeeDTO;
import com.example.employeeManagement.Repository.EmployeeRepository;
import com.example.employeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp/")
public class EmployeeController {


    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    // Get employee Details


    @CrossOrigin(origins = "*") // You can specify allowed origins here
    @GetMapping("/allEmployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
       List<EmployeeDTO>list =  employeeService.getAllEmployees();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @CrossOrigin(origins = "*") // You can specify allowed origins here
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        System.out.println("hello");
        return String.format("Hello %s!", name);
    }

    // Save operation
    @CrossOrigin(origins = "*") // You can specify allowed origins here
    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*") // You can specify allowed origins here
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable String employeeId, @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeId, employeeDTO);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }


    @CrossOrigin(origins = "*") // You can specify allowed origins here
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }


}
