package com.airtel.Device_inventory_system.controller;



import org.springframework.web.bind.annotation.*;

import com.airtel.Device_inventory_system.model.Device;
import com.airtel.Device_inventory_system.service.DeviceService;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // ADD DEVICE
    @PostMapping
    public Device addDevice(@RequestBody Device device) {
        return deviceService.saveDevice(device);
    }

    // GET ALL DEVICES
    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // GET AVAILABLE DEVICES
    @GetMapping("/available")
    public List<Device> getAvailableDevices() {
        return deviceService.getAvailableDevices();
    }

    // GET DEVICE BY ID
    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }
}