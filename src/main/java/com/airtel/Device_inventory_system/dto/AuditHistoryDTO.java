package com.airtel.Device_inventory_system.dto;


import java.time.LocalDateTime;

public class AuditHistoryDTO {
    private Long id;
    private String deviceName;
    private String userName;
    private String action;        // e.g. ASSIGNED, RETURNED, MAINTENANCE
    private LocalDateTime timestamp;
    private String notes;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}