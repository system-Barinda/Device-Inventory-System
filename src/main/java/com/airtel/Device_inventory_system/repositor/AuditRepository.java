package com.airtel.Device_inventory_system.repositor;

import org.springframework.data.jpa.repository.JpaRepository;


import com.airtel.Device_inventory_system.model.AuditHistory;

import java.util.List;

public interface AuditRepository extends JpaRepository<AuditHistory, Long> {

    List<AuditHistory> findByDeviceDeviceId(Long deviceId);

    List<AuditHistory> findByUserUserId(Long userId);
}