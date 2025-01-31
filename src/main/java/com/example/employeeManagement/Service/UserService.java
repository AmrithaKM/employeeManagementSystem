package com.example.employeeManagement.Service;

import com.example.employeeManagement.Modal.UserReg;
import com.example.employeeManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Use PasswordEncoder interface for flexibility

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // Autowire the PasswordEncoder
    }

    // Method to create a new user
    public boolean createUser(UserReg user) {
        // Check if a user with the same username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false; // Username already exists
        }

        // Hash the user's password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword()); // Encode the password
        user.setPassword(encodedPassword); // Set the encoded password

        // Save the user to the database
        userRepository.save(user);
        return true; // User created successfully
    }

    // Method to validate a user's credentials (for login)
    public boolean validateUser(UserReg user) {
        UserReg existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // Validate the password
            return passwordEncoder.matches(user.getPassword(), existingUser.getPassword());
        }
        return false; // User doesn't exist
    }
}
