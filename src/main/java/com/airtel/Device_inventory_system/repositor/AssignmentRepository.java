package com.airtel.Device_inventory_system.repositor;



import com.airtel.Device_inventory_system.model.Assignment;



import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}