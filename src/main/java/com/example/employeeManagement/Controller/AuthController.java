package com.example.employeeManagement.Controller;

import com.example.employeeManagement.Modal.UserReg;
import com.example.employeeManagement.Security.JwtUtil;
import com.example.employeeManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    // Authenticate user and generate JWT token
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserReg user) {
        try {
            if (userService.validateUser(user)) {
                String token = jwtUtil.generateToken(user.getUsername());
                return ResponseEntity.ok(token);  // Return token with 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while authenticating: " + e.getMessage());
        }
    }

    // Register a new user
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserReg user) {
        try {
            boolean userCreated = userService.createUser(user); // Assuming createUser handles registration
            if (userCreated) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("User created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User registration failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the user: " + e.getMessage());
        }
    }
}
