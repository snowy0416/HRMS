package com.example.attendance_tracking_backend.controller;

import com.example.attendance_tracking_backend.dto.AttendanceRequest;
import com.example.attendance_tracking_backend.dto.AttendanceRecord;
import com.example.attendance_tracking_backend.model.Attendance;
import com.example.attendance_tracking_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Attendance> markAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        Attendance savedAttendance = attendanceService.saveAttendance(attendanceRequest);
        return ResponseEntity.ok(savedAttendance);
    }

    @GetMapping("/timeAtWork")
    public ResponseEntity<List<AttendanceRecord>> getTimeAtWork() {
        List<AttendanceRecord> timeAtWork = attendanceService.getTimeAtWork();
        return ResponseEntity.ok(timeAtWork);
    }
}

