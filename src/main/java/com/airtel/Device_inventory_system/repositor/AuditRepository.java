package com.airtel.Device_inventory_system.repositor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.airtel.Device_inventory_system.model.AuditHistory;

public interface AuditRepository extends JpaRepository<AuditHistory, Long> {

    // ✅ find by device
    List<AuditHistory> findByDevice_DeviceId(Long deviceId);

    // ✅ find by employee
    List<AuditHistory> findByEmployee_EmployeeId(Long employeeId);
}