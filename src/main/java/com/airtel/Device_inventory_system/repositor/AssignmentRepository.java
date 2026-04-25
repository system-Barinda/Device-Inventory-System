package com.airtel.Device_inventory_system.repositor;


import com.airtel.Device_inventory_system.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // ✅ Fetch all assignments with device and employee in one query
    // Prevents LazyInitializationException when serializing to JSON
    @Query("SELECT a FROM Assignment a LEFT JOIN FETCH a.device LEFT JOIN FETCH a.employee")
    List<Assignment> findAllWithDeviceAndEmployee();
}