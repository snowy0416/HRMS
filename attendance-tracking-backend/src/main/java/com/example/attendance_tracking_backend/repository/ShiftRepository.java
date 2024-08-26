package com.example.attendance_tracking_backend.repository;

import com.example.attendance_tracking_backend.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}

