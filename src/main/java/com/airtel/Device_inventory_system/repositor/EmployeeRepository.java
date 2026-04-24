package com.airtel.Device_inventory_system.repositor;


import org.springframework.data.jpa.repository.JpaRepository;
import com.airtel.Device_inventory_system.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
