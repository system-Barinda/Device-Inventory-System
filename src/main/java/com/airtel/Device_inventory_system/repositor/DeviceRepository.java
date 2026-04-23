package com.airtel.Device_inventory_system.repositor;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airtel.Device_inventory_system.model.Device;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByStatus(String status);

    List<Device> findByType(String type);
}
