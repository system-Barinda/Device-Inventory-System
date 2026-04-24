package com.airtel.Device_inventory_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airtel.Device_inventory_system.model.Device;
import com.airtel.Device_inventory_system.service.DeviceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
/**
 * ✅ FIX: You cannot use "*" with allowCredentials = "true".
 * Change the origin to match your frontend URL (e.g., your VS Code Live Server address).
 */
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true") 
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // ✅ ADD DEVICE
    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        System.out.println("Incoming Device: " + device);

        if (device.getDeviceName() == null || device.getDeviceName().isEmpty()) {
            return ResponseEntity.badRequest().body("Device name is required");
        }

        try {
            Device savedDevice = deviceService.saveDevice(device);
            return ResponseEntity.ok(savedDevice);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving device: " + e.getMessage());
        }
    }

    // ✅ GET ALL DEVICES
    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    // ✅ GET AVAILABLE DEVICES
    @GetMapping("/available")
    public ResponseEntity<List<Device>> getAvailableDevices() {
        return ResponseEntity.ok(deviceService.getAvailableDevices());
    }

    // ✅ GET DEVICE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDevice(@PathVariable Long id) {
        Optional<Device> device = deviceService.getDeviceById(id);

        if (device.isPresent()) {
            return ResponseEntity.ok(device.get());
        } else {
            return ResponseEntity.status(404).body("Device not found");
        }
    }
}