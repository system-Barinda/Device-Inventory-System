package com.airtel.Device_inventory_system.controller;

import com.airtel.Device_inventory_system.model.AuditHistory;
import com.airtel.Device_inventory_system.service.AuditService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
/**
 * ✅ FIXED: Added allowCredentials = "true"
 * This is the "handshake" that allows your frontend to send its login session.
 */
@CrossOrigin(
    origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, 
    allowCredentials = "true"
)
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    // ✅ GET ALL HISTORY
    @GetMapping
    public ResponseEntity<?> getAllHistory() {
        try {
            List<AuditHistory> history = auditService.getAllHistory();
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching audit history: " + e.getMessage());
        }
    }

    // ✅ GET HISTORY BY DEVICE
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<?> getByDevice(@PathVariable Long deviceId) {
        try {
            List<AuditHistory> history = auditService.getHistoryByDevice(deviceId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching device history: " + e.getMessage());
        }
    }

    // ✅ GET HISTORY BY EMPLOYEE
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getByEmployee(@PathVariable Long employeeId) {
        try {
            List<AuditHistory> history = auditService.getHistoryByEmployee(employeeId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching employee history: " + e.getMessage());
        }
    }
}