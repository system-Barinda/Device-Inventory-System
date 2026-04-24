package com.airtel.Device_inventory_system.service;



import org.springframework.stereotype.Service;
import com.airtel.Device_inventory_system.model.User;
import com.airtel.Device_inventory_system.repositor.UserRepository;


@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER
    public String register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        // Save user (password comes from your UI)
        userRepository.save(user);
        return "User registered successfully";
    }

    // LOGIN
    public boolean login(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}