package com.example.attendance_tracking_backend.repository;

import com.example.attendance_tracking_backend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    
    // Custom query method to find attendance records by employee ID
    List<Attendance> findByEmpIdOrderByTimestampDesc(String empId);
    
    // Additional method to calculate total hours (if needed in future)
    // Assuming checkIn is true for check-ins and false for check-outs
    List<Attendance> findByEmpIdAndCheckInFalse(String empId);
}
