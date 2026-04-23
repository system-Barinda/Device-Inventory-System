package com.airtel.Device_inventory_system.controller;


import org.springframework.web.bind.annotation.*;

import com.airtel.Device_inventory_system.model.Assignment;
import com.airtel.Device_inventory_system.service.AssignmentService;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // ASSIGN DEVICE
    @PostMapping("/assign")
    public Assignment assignDevice(
            @RequestParam Long deviceId,
            @RequestParam Long userId) {

        return assignmentService.assignDevice(deviceId, userId);
    }

    // RETURN DEVICE
    @PostMapping("/return/{assignmentId}")
    public Assignment returnDevice(@PathVariable Long assignmentId) {
        return assignmentService.returnDevice(assignmentId);
    }
}