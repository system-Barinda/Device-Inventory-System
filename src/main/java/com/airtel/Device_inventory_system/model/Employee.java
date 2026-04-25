package com.airtel.Device_inventory_system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "department")
    private String department;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ✅ Break circular loop: Employee → assignments → Employee → ...
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"device", "employee", "hibernateLazyInitializer", "handler"})
    private List<Assignment> assignments;

    // ✅ Auto set timestamp
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ─── Constructors ─────────────────────────────────────────────────────────
    public Employee() {}

    // ─── Getters & Setters ────────────────────────────────────────────────────
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    // ❌ No setCreatedAt — controlled by @PrePersist only

    public List<Assignment> getAssignments() { return assignments; }
    public void setAssignments(List<Assignment> assignments) { this.assignments = assignments; }
}