package com.airtel.Device_inventory_system.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

import com.airtel.Device_inventory_system.model.*;
import com.airtel.Device_inventory_system.repositor.AssignmentRepository;
import com.airtel.Device_inventory_system.repositor.DeviceRepository;
import com.airtel.Device_inventory_system.repositor.EmployeeRepository;


@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final DeviceRepository deviceRepository;
    private final EmployeeRepository employeeRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             DeviceRepository deviceRepository,
                             EmployeeRepository employeeRepository) {
        this.assignmentRepository = assignmentRepository;
        this.deviceRepository = deviceRepository;
        this.employeeRepository = employeeRepository;
    }

    public Assignment assignDevice(Long deviceId, Long employeeId) {

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // 🚨 prevent double assignment
        if("Assigned".equalsIgnoreCase(device.getStatus())){
            throw new RuntimeException("Device already assigned");
        }

        // ✅ create assignment
        Assignment assignment = new Assignment();
        assignment.setDevice(device);
        assignment.setEmployee(employee);
        assignment.setAssignedDate(LocalDate.now());
        assignment.setStatus("Active");

        // ✅ update device status
        device.setStatus("Assigned");

        deviceRepository.save(device);

        return assignmentRepository.save(assignment);
    }
}