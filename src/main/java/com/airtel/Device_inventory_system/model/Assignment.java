package com.airtel.Device_inventory_system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    // ✅ Only serialize specific fields from Device — breaks circular loop
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id")
    @JsonIgnoreProperties({"assignments", "hibernateLazyInitializer", "handler"})
    private Device device;

    // ✅ Only serialize specific fields from Employee — breaks circular loop
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"assignments", "password", "hibernateLazyInitializer", "handler"})
    private Employee employee;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status")
    private String status;

    // ─── Constructor ──────────────────────────────────────────────────────────
    public Assignment() {}

    // ─── Getters & Setters ────────────────────────────────────────────────────
    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public LocalDate getAssignedDate() { return assignedDate; }
    public void setAssignedDate(LocalDate assignedDate) { this.assignedDate = assignedDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}