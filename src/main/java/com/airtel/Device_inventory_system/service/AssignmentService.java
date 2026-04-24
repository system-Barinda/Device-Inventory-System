package com.airtel.Device_inventory_system.service;



import org.springframework.stereotype.Service;
import com.airtel.Device_inventory_system.model.Assignment;
import com.airtel.Device_inventory_system.model.AuditHistory;
import com.airtel.Device_inventory_system.model.Device;
import com.airtel.Device_inventory_system.model.User;
import com.airtel.Device_inventory_system.repositor.AssignmentRepository;
import com.airtel.Device_inventory_system.repositor.AuditRepository;
import com.airtel.Device_inventory_system.repositor.DeviceRepository;
import com.airtel.Device_inventory_system.repositor.UserRepository;

import java.time.LocalDate;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final AuditRepository auditRepository;

    public AssignmentService(
            AssignmentRepository assignmentRepository,
            DeviceRepository deviceRepository,
            UserRepository userRepository,
            AuditRepository auditRepository) {
        this.assignmentRepository = assignmentRepository;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.auditRepository = auditRepository;
    }

    // ASSIGN DEVICE
    public Assignment assignDevice(Long deviceId, Long userId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        if (!device.getStatus().equals("available")) {
            throw new RuntimeException("Device is not available");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Assignment assignment = new Assignment();
        assignment.setDevice(device);
        assignment.setUser(user);
        assignment.setAssignedDate(LocalDate.now());
        assignment.setStatus("assigned");

        device.setStatus("assigned");
        deviceRepository.save(device);

        Assignment savedAssignment = assignmentRepository.save(assignment);

        // SAVE AUDIT ✅ Fixed
        AuditHistory audit = new AuditHistory();
        audit.setDevice(device);
        audit.setUser(user);
        audit.setAction("assigned");
        auditRepository.save(audit);

        return savedAssignment;
    }

    // RETURN DEVICE
    public Assignment returnDevice(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        assignment.setReturnDate(LocalDate.now());
        assignment.setStatus("returned");

        Device device = assignment.getDevice();
        device.setStatus("available");
        deviceRepository.save(device);

        Assignment updated = assignmentRepository.save(assignment);

        // SAVE AUDIT ✅ Already correct
        AuditHistory audit = new AuditHistory();
        audit.setDevice(device);
        audit.setUser(assignment.getUser());
        audit.setAction("returned");
        auditRepository.save(audit);

        return updated;
    }
}