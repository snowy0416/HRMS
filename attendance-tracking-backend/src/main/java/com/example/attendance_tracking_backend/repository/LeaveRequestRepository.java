package com.example.attendance_tracking_backend.repository;

import com.example.attendance_tracking_backend.model.LeaveRequest; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}
