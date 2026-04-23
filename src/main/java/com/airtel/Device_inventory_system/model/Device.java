package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}