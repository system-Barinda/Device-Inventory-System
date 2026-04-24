package com.airtel.Device_inventory_system.controller;

import com.airtel.Device_inventory_system.model.AuditHistory;
import com.airtel.Device_inventory_system.service.AuditService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
@CrossOrigin(origins = "*") // ✅ allow frontend access
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    // ✅ GET ALL HISTORY
    @GetMapping
    public List<AuditHistory> getAllHistory() {
        return auditService.getAllHistory();
    }

    // ✅ GET HISTORY BY DEVICE (for timeline page)
    @GetMapping("/device/{deviceId}")
    public List<AuditHistory> getByDevice(@PathVariable Long deviceId) {
        return auditService.getHistoryByDevice(deviceId);
    }

    // ✅ FIXED: use EMPLOYEE instead of USER
    @GetMapping("/employee/{employeeId}")
    public List<AuditHistory> getByEmployee(@PathVariable Long employeeId) {
        return auditService.getHistoryByEmployee(employeeId);
    }
}