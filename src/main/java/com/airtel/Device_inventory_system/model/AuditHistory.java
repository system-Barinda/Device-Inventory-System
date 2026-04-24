package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_history")
public class AuditHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String action; // assigned, returned, updated
    private LocalDateTime actionDate = LocalDateTime.now();

    // ✅ Constructors
    public AuditHistory() {}

    public AuditHistory(Long auditId, Device device, User user,
                        String action, LocalDateTime actionDate) {
        this.auditId = auditId;
        this.device = device;
        this.user = user;
        this.action = action;
        this.actionDate = actionDate;
    }

    // ✅ Getters
    public Long getAuditId() { return auditId; }
    public Device getDevice() { return device; }
    public User getUser() { return user; }
    public String getAction() { return action; }
    public LocalDateTime getActionDate() { return actionDate; }

    // ✅ Setters
    public void setAuditId(Long auditId) { this.auditId = auditId; }
    public void setDevice(Device device) { this.device = device; }
    public void setUser(User user) { this.user = user; }
    public void setAction(String action) { this.action = action; }
    public void setActionDate(LocalDateTime actionDate) { this.actionDate = actionDate; }

    // ✅ toString
    @Override
    public String toString() {
        return "AuditHistory{" +
                "auditId=" + auditId +
                ", device=" + device +
                ", user=" + user +
                ", action='" + action + '\'' +
                ", actionDate=" + actionDate +
                '}';
    }
}