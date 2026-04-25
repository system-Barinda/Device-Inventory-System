package com.airtel.Device_inventory_system.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.airtel.Device_inventory_system.model.Assignment;
import com.airtel.Device_inventory_system.repositor.AssignmentRepository;
import com.airtel.Device_inventory_system.service.AssignmentService;

@RestController
@RequestMapping("/api/assignments")
// ✅ NO @CrossOrigin here — CORS handled globally in SecurityConfig/WebConfig
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;

    public AssignmentController(AssignmentService assignmentService,
                                AssignmentRepository assignmentRepository) {
        this.assignmentService = assignmentService;
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * ✅ ASSIGN DEVICE
     * Endpoint: POST http://localhost:8080/api/assignments/assign/{deviceId}/{employeeId}
     */
    @PostMapping("/assign/{deviceId}/{employeeId}")
    public ResponseEntity<?> assignDevice(@PathVariable Long deviceId,
                                          @PathVariable Long employeeId) {
        try {
            Assignment newAssignment = assignmentService.assignDevice(deviceId, employeeId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAssignment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * ✅ GET ALL ASSIGNMENTS
     * Endpoint: GET http://localhost:8080/api/assignments
     */
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        // ✅ Use JOIN FETCH query to avoid LazyInitializationException
        List<Assignment> assignments = assignmentRepository.findAllWithDeviceAndEmployee();
        return ResponseEntity.ok(assignments);
    }
}