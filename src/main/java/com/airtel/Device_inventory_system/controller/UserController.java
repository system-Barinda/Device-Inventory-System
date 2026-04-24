package com.airtel.Device_inventory_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.airtel.Device_inventory_system.model.User;
import com.airtel.Device_inventory_system.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth") 
@CrossOrigin(originPatterns = "*", allowCredentials = "true") 
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * LOGIN: Manually authenticates the user and creates a session.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpServletRequest request) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Use userService instead of userRepository
        User user = userService.findByEmail(email);
        
        if (user != null && user.getPassword().equals(password)) {
            // 1. Create the Authentication token
            UsernamePasswordAuthenticationToken authReq = 
                new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
            
            // 2. Set the Security Context
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authReq);

            // 3. Manually create/get the session and store the context
            // This ensures the JSESSIONID cookie is linked to this authenticated state
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", sc);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid email or password"));
        }
    }

    /**
     * REGISTER: Create a new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            if (userService.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                     .body(Map.of("message", "User with this email already exists"));
            }
            
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "Registration failed: " + e.getMessage()));
        }
    }

    /**
     * USER MANAGEMENT
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}