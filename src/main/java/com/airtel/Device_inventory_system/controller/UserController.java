package com.airtel.Device_inventory_system.controller;


import org.springframework.web.bind.annotation.*;

import com.airtel.Device_inventory_system.model.User;
import com.airtel.Device_inventory_system.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}