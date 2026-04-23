package com.airtel.Device_inventory_system.controller;



import org.springframework.web.bind.annotation.*;

import com.airtel.Device_inventory_system.model.AuditHistory;
import com.airtel.Device_inventory_system.service.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    // GET ALL HISTORY
    @GetMapping
    public List<AuditHistory> getAllHistory() {
        return auditService.getAllHistory();
    }
}