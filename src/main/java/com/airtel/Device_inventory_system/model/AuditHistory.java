package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_history")
public class AuditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Long auditId;

    // ✅ RELATION WITH DEVICE
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    // ✅ FIXED: must match database column EXACTLY
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // ✅ ACTION (ASSIGNED, RETURNED, UPDATED)
    @Column(name = "action", nullable = false)
    private String action;

    // ✅ AUTO DATE
    @Column(name = "action_date", nullable = false, updatable = false)
    private LocalDateTime actionDate;

    // ✅ AUTO SET DATE BEFORE INSERT
    @PrePersist
    protected void onCreate() {
        this.actionDate = LocalDateTime.now();
    }

    // ✅ Constructors
    public AuditHistory() {}

    public AuditHistory(Device device, Employee employee, String action) {
        this.device = device;
        this.employee = employee;
        this.action = action;
    }

    // ✅ Getters
    public Long getAuditId() { return auditId; }
    public Device getDevice() { return device; }
    public Employee getEmployee() { return employee; }
    public String getAction() { return action; }
    public LocalDateTime getActionDate() { return actionDate; }

    // ✅ Setters
    public void setAuditId(Long auditId) { this.auditId = auditId; }
    public void setDevice(Device device) { this.device = device; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setAction(String action) { this.action = action; }

    // ❌ DO NOT manually set actionDate (auto handled)
    // public void setActionDate(LocalDateTime actionDate) { this.actionDate = actionDate; }

    // ✅ toString (safe + clean)
    @Override
    public String toString() {
        return "AuditHistory{" +
                "auditId=" + auditId +
                ", deviceId=" + (device != null ? device.getDeviceId() : null) +
                ", employeeId=" + (employee != null ? employee.getEmployeeId() : null) +
                ", action='" + action + '\'' +
                ", actionDate=" + actionDate +
                '}';
    }
}