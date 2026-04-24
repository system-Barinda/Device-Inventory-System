package com.airtel.Device_inventory_system.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

import com.airtel.Device_inventory_system.model.*;
import com.airtel.Device_inventory_system.repositor.AssignmentRepository;
import com.airtel.Device_inventory_system.repositor.DeviceRepository;
import com.airtel.Device_inventory_system.repositor.EmployeeRepository;
import com.airtel.Device_inventory_system.repositor.AuditRepository;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final DeviceRepository deviceRepository;
    private final EmployeeRepository employeeRepository;
    private final AuditRepository auditRepository; // ✅ FIXED

    // ✅ CONSTRUCTOR INJECTION
    public AssignmentService(AssignmentRepository assignmentRepository,
                             DeviceRepository deviceRepository,
                             EmployeeRepository employeeRepository,
                             AuditRepository auditRepository) {
        this.assignmentRepository = assignmentRepository;
        this.deviceRepository = deviceRepository;
        this.employeeRepository = employeeRepository;
        this.auditRepository = auditRepository; // ✅ FIXED
    }

    public Assignment assignDevice(Long deviceId, Long employeeId) {

        // ✅ 1. FETCH DATA
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // 🚨 2. PREVENT DOUBLE ASSIGNMENT
        if ("Assigned".equalsIgnoreCase(device.getStatus())) {
            throw new RuntimeException("Device already assigned");
        }

        // ✅ 3. CREATE ASSIGNMENT
        Assignment assignment = new Assignment();
        assignment.setDevice(device);
        assignment.setEmployee(employee);
        assignment.setAssignedDate(LocalDate.now());
        assignment.setStatus("ACTIVE");

        // ✅ 4. UPDATE DEVICE STATUS
        device.setStatus("Assigned");
        deviceRepository.save(device);

        // ✅ 5. SAVE ASSIGNMENT
        Assignment savedAssignment = assignmentRepository.save(assignment);

        // 🔥 6. SAVE AUDIT HISTORY (AUTOMATIC)
        AuditHistory audit = new AuditHistory(
                device,
                employee,
                "ASSIGNED to " + employee.getFirstName()
        );

        auditRepository.save(audit); // ✅ FIXED

        return savedAssignment;
    }
}