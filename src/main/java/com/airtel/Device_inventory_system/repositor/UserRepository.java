package com.airtel.Device_inventory_system.repositor;

import org.springframework.data.jpa.repository.JpaRepository;


import com.airtel.Device_inventory_system.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}