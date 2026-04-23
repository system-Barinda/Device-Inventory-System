package com.airtel.Device_inventory_system.service;





import org.springframework.stereotype.Service;

import com.airtel.Device_inventory_system.repositor.AuditRepository;

import java.util.List;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public List<AuditRepository> getAllHistory() {
        return auditRepository.findAll();
    }
}