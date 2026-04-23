package com.airtel.Device_inventory_system.model;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}