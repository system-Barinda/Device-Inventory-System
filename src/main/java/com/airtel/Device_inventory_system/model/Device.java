package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "device_name", nullable = false)
    private String deviceName;

    @Column(name = "type")
    private String type;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "status")
    private String status;

    @Column(name = "condition_status")
    private String conditionStatus;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ✅ AUTO SET TIMESTAMP (BEST PRACTICE)
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ✅ Constructors
    public Device() {}

    public Device(Long deviceId, String deviceName, String type, String serialNumber,
                  String brand, String model, String status, String conditionStatus,
                  LocalDate purchaseDate) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.type = type;
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.conditionStatus = conditionStatus;
        this.purchaseDate = purchaseDate;
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

    // ❌ REMOVE setCreatedAt (DB/PrePersist should control it)

    // ✅ toString
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