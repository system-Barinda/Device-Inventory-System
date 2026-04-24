package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate assignedDate;
    private LocalDate returnDate;
    private String status; // assigned, returned

    // ✅ Constructors
    public Assignment() {}

    public Assignment(Long assignmentId, Device device, User user,
                      LocalDate assignedDate, LocalDate returnDate, String status) {
        this.assignmentId = assignmentId;
        this.device = device;
        this.user = user;
        this.assignedDate = assignedDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // ✅ Getters
    public Long getAssignmentId() { return assignmentId; }
    public Device getDevice() { return device; }
    public User getUser() { return user; }
    public LocalDate getAssignedDate() { return assignedDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getStatus() { return status; }

    // ✅ Setters
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }
    public void setDevice(Device device) { this.device = device; }
    public void setUser(User user) { this.user = user; }
    public void setAssignedDate(LocalDate assignedDate) { this.assignedDate = assignedDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setStatus(String status) { this.status = status; }

    // ✅ toString
    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", device=" + device +
                ", user=" + user +
                ", assignedDate=" + assignedDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}