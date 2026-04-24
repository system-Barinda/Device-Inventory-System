package com.airtel.Device_inventory_system.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.airtel.Device_inventory_system.model.Assignment;
import com.airtel.Device_inventory_system.repositor.AssignmentRepository;
import com.airtel.Device_inventory_system.service.AssignmentService;


@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;

    public AssignmentController(AssignmentService assignmentService,
                                AssignmentRepository assignmentRepository) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
    }

    // ✅ ASSIGN DEVICE
    @PostMapping("/{deviceId}/{employeeId}")
    public Assignment assignDevice(@PathVariable Long deviceId,
                                   @PathVariable Long employeeId) {
        return assignmentService.assignDevice(deviceId, employeeId);
    }

    // ✅ GET ALL ASSIGNMENTS
    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
}