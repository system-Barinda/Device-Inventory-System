package com.airtel.Device_inventory_system.repositor;

import org.springframework.data.jpa.repository.JpaRepository;


import com.airtel.Device_inventory_system.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA will automatically implement this query based on the method name
    Optional<User> findByEmail(String email);
}