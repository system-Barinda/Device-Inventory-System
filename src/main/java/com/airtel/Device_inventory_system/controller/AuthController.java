package com.airtel.Device_inventory_system.controller;

import com.airtel.Device_inventory_system.model.User;
import com.airtel.Device_inventory_system.repositor.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Email and password are required"
            ));
        }

        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            return ResponseEntity.status(400).body(Map.of(
                    "message", "Email already registered"
            ));
        }

        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "message", "Registered successfully"
        ));
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body,
                                  HttpServletRequest request) {

        String email = body.get("email");
        String password = body.get("password");

        // ✅ VALIDATION
        if (email == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Email and password are required"
            ));
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of(
                    "message", "User not found"
            ));
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body(Map.of(
                    "message", "Invalid password"
            ));
        }

        // ✅ CREATE SESSION
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        // ✅ RESPONSE
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("userId", user.getUserId());
        response.put("fullName", user.getFullName());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }

    // ================= LOGOUT =================
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok(Map.of(
                "message", "Logged out successfully"
        ));
    }
}