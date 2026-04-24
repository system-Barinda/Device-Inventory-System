package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    private String deviceName;
    private String type;
    private String serialNumber;
    private String brand;
    private String model;
    private String status; // available, assigned, under_repair
    private String conditionStatus; // good, damaged
    private LocalDate purchaseDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    // ✅ Constructors
    public Device() {}

    public Device(Long deviceId, String deviceName, String type, String serialNumber,
                  String brand, String model, String status, String conditionStatus,
                  LocalDate purchaseDate, LocalDateTime createdAt) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.type = type;
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.conditionStatus = conditionStatus;
        this.purchaseDate = purchaseDate;
        this.createdAt = createdAt;
    }

    // ✅ Getters
    public Long getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public String getType() { return type; }
    public String getSerialNumber() { return serialNumber; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getStatus() { return status; }
    public String getConditionStatus() { return conditionStatus; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ✅ Setters
    public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public void setType(String type) { this.type = type; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setStatus(String status) { this.status = status; }
    public void setConditionStatus(String conditionStatus) { this.conditionStatus = conditionStatus; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // ✅ toString (optional but useful for debugging)
    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", type='" + type + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", status='" + status + '\'' +
                ", conditionStatus='" + conditionStatus + '\'' +
                '}';
    }
}