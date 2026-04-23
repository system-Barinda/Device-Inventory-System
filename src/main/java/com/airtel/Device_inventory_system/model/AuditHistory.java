package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}