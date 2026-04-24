package com.airtel.Device_inventory_system.controller;

import com.airtel.Device_inventory_system.model.User;
import com.airtel.Device_inventory_system.repositor.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Check if email already exists
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.status(400).body("Email already registered");
        }
        userRepository.save(user);
        return ResponseEntity.ok("Registered successfully");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        // ✅ Fix — unwrap Optional with orElse(null)
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}