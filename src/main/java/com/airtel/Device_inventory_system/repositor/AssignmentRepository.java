package com.airtel.Device_inventory_system.repositor;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airtel.Device_inventory_system.model.Assignment;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByUserUserId(Long userId);

    List<Assignment> findByDeviceDeviceId(Long deviceId);
}