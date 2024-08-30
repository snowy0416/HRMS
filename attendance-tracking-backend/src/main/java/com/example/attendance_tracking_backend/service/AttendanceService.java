package com.example.attendance_tracking_backend.service;

import com.example.attendance_tracking_backend.dto.AttendanceRequest;
import com.example.attendance_tracking_backend.dto.AttendanceRecord;
import com.example.attendance_tracking_backend.model.Attendance;
import com.example.attendance_tracking_backend.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance saveAttendance(AttendanceRequest attendanceRequest) {
        Attendance attendance = new Attendance();
        attendance.setEmpId(attendanceRequest.getEmpId());
        attendance.setNote(attendanceRequest.getNote());
        attendance.setCheckIn(attendanceRequest.isCheckIn());
        attendance.setTimestamp(LocalDateTime.now());

        // Save the attendance record
        return attendanceRepository.save(attendance);
    }

    public List<AttendanceRecord> getTimeAtWork() {
        List<Attendance> allAttendances = attendanceRepository.findAll();

        // Group attendance records by employee ID
        Map<String, List<Attendance>> groupedByEmpId = allAttendances.stream()
            .sorted(Comparator.comparing(Attendance::getTimestamp)) // Sort by timestamp
            .collect(Collectors.groupingBy(Attendance::getEmpId));

        List<AttendanceRecord> attendanceRecords = new ArrayList<>();

        for (Map.Entry<String, List<Attendance>> entry : groupedByEmpId.entrySet()) {
            String empId = entry.getKey();
            List<Attendance> records = entry.getValue();

            // Separate check-in and check-out records
            List<Attendance> checkInRecords = records.stream().filter(Attendance::isCheckIn).sorted(Comparator.comparing(Attendance::getTimestamp)).collect(Collectors.toList());
            List<Attendance> checkOutRecords = records.stream().filter(record -> !record.isCheckIn()).sorted(Comparator.comparing(Attendance::getTimestamp)).collect(Collectors.toList());

            // Pair check-in and check-out records
            int checkOutIndex = 0;
            for (Attendance checkIn : checkInRecords) {
                if (checkOutIndex < checkOutRecords.size()) {
                    Attendance checkOut = checkOutRecords.get(checkOutIndex);
                    if (checkOut.getTimestamp().isAfter(checkIn.getTimestamp())) {
                        String timeIn = checkIn.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                        String timeOut = checkOut.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                        String totalHours = calculateTotalHours(Optional.of(checkIn), Optional.of(checkOut));
                        attendanceRecords.add(new AttendanceRecord(empId, timeIn, timeOut, totalHours));
                        checkOutIndex++;
                    }
                } else {
                    // No matching check-out record
                    String timeIn = checkIn.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    attendanceRecords.add(new AttendanceRecord(empId, timeIn, "N/A", "In Progress"));
                }
            }

            // Handle any remaining check-out records with no matching check-in
            while (checkOutIndex < checkOutRecords.size()) {
                Attendance checkOut = checkOutRecords.get(checkOutIndex);
                attendanceRecords.add(new AttendanceRecord(empId, "N/A", checkOut.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), "In Progress"));
                checkOutIndex++;
            }
        }

        return attendanceRecords;
    }

    private String calculateTotalHours(Optional<Attendance> checkIn, Optional<Attendance> checkOut) {
        if (checkIn.isPresent() && checkOut.isPresent()) {
            Duration duration = Duration.between(checkIn.get().getTimestamp(), checkOut.get().getTimestamp());
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            return String.format("%02d:%02d", hours, minutes);
        }
        return "In Progress";
    }
}

