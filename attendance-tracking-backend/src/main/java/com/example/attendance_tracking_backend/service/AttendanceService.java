package com.example.attendance_tracking_backend.service;

import com.example.attendance_tracking_backend.dto.AttendanceRequest;
import com.example.attendance_tracking_backend.model.Attendance;
import com.example.attendance_tracking_backend.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance saveAttendance(AttendanceRequest attendanceRequest) {
        Attendance attendance = new Attendance();
        attendance.setEmpId(attendanceRequest.getEmpId());
        attendance.setNote(attendanceRequest.getNote());
        attendance.setCheckIn(attendanceRequest.isCheckIn());
        attendance.setTimestamp(LocalDateTime.now()); // Save current timestamp as LocalDateTime
        
        // Save the attendance record
        return attendanceRepository.save(attendance);
    }
}
