package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;
    private String email;
    private String department;
    private String role;

    private LocalDateTime createdAt = LocalDateTime.now();
}