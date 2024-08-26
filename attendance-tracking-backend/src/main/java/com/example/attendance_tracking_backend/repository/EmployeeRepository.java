package com.example.attendance_tracking_backend.repository;

import com.example.attendance_tracking_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

