package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;
    private String email;
    private String department;
    private String role;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();

    // ✅ Constructors
    public User() {}

    public User(Long userId, String fullName, String email,
            String department, String role, String password, LocalDateTime createdAt) {
    this.userId = userId;
    this.fullName = fullName;
    this.email = email;
    this.department = department;
    this.role = role;
    this.password = password;
    this.createdAt = createdAt;
}

    // ✅ Getters
    public Long getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public String getPassword() { return password; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ✅ Setters
    public void setUserId(Long userId) { this.userId = userId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setRole(String role) { this.role = role; }
    public void setPassword(String password) { this.password = password; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    

    // ✅ toString
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}