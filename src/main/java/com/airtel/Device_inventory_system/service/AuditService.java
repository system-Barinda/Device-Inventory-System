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

    // ✅ GET ALL HISTORY
    public List<AuditHistory> getAllHistory() {
        return auditRepository.findAll();
    }

    // ✅ GET HISTORY BY DEVICE
    public List<AuditHistory> getHistoryByDevice(Long deviceId) {
        return auditRepository.findByDevice_DeviceId(deviceId);
    }

    // ✅ FIXED: use Employee instead of User
    public List<AuditHistory> getHistoryByEmployee(Long employeeId) {
        return auditRepository.findByEmployee_EmployeeId(employeeId);
    }
}