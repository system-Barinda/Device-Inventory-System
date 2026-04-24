package com.airtel.Device_inventory_system.service;




import org.springframework.stereotype.Service;

import com.airtel.Device_inventory_system.model.Device;
import com.airtel.Device_inventory_system.repositor.DeviceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device saveDevice(Device device) {
        device.setStatus("available"); // default status
        return deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> getAvailableDevices() {
        return deviceRepository.findByStatus("available");
    }

    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }
}