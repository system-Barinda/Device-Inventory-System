package com.airtel.Device_inventory_system.service;



import com.airtel.Device_inventory_system.model.AuditHistory;
import com.airtel.Device_inventory_system.repositor.AuditRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditService {
    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public List<AuditHistory> getAllHistory() {
        return auditRepository.findAll();
    }

    public List<AuditHistory> getHistoryByDevice(Long deviceId) {
        return auditRepository.findByDeviceDeviceId(deviceId);
    }

    public List<AuditHistory> getHistoryByUser(Long userId) {
        return auditRepository.findByUserUserId(userId);
    }
}