package com.airtel.Device_inventory_system.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import com.airtel.Device_inventory_system.model.*;
import com.airtel.Device_inventory_system.repositor.AssignmentRepository;
import com.airtel.Device_inventory_system.repositor.AuditRepository;
import com.airtel.Device_inventory_system.repositor.DeviceRepository;
import com.airtel.Device_inventory_system.repositor.EmployeeRepository;


@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final DeviceRepository deviceRepository;
    private final EmployeeRepository employeeRepository;
    private final AuditRepository auditRepository;

    // ✅ CONSTRUCTOR INJECTION
    public AssignmentService(AssignmentRepository assignmentRepository,
                             DeviceRepository deviceRepository,
                             EmployeeRepository employeeRepository,
                             AuditRepository auditRepository) {
        this.assignmentRepository = assignmentRepository;
        this.deviceRepository = deviceRepository;
        this.employeeRepository = employeeRepository;
        this.auditRepository = auditRepository;
    }

    public Assignment assignDevice(Long deviceId, Long employeeId) {

        // ✅ 1. FETCH DEVICE
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + deviceId));

        // ✅ 2. FETCH EMPLOYEE
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        // 🚨 3. PREVENT DOUBLE ASSIGNMENT
        if ("Assigned".equalsIgnoreCase(device.getStatus())) {
            throw new RuntimeException("Device '" + device.getDeviceName() + "' is already assigned");
        }

        // ✅ 4. CREATE ASSIGNMENT
        Assignment assignment = new Assignment();
        assignment.setDevice(device);
        assignment.setEmployee(employee);
        assignment.setAssignedDate(LocalDate.now());
        assignment.setStatus("ACTIVE");

        // ✅ 5. UPDATE DEVICE STATUS
        device.setStatus("Assigned");
        deviceRepository.save(device);

        // ✅ 6. SAVE ASSIGNMENT
        Assignment savedAssignment = assignmentRepository.save(assignment);

        // ✅ 7. SAVE AUDIT HISTORY
        try {
            AuditHistory audit = new AuditHistory(
                    device,
                    employee,
                    "ASSIGNED to " + employee.getFirstName() + " " + employee.getLastName()
            );
            auditRepository.save(audit);
        } catch (Exception e) {
            // Don't fail the whole assignment if audit logging fails
            System.err.println("⚠ Audit log failed (non-critical): " + e.getMessage());
        }

        return savedAssignment;
    }
}