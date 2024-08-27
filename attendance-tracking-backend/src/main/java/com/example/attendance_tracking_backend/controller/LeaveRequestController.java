package com.example.attendance_tracking_backend.controller;

import com.example.attendance_tracking_backend.model.LeaveRequest;
import com.example.attendance_tracking_backend.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<LeaveRequest> submitLeaveRequest(
        @RequestParam("name") String name,
        @RequestParam("employeeId") String employeeId,
        @RequestParam("position") String position,
        @RequestParam("contactNumber") String contactNumber,
        @RequestParam("emailAddress") String emailAddress,
        @RequestParam("leaveType") String leaveType,
        @RequestParam("leaveStartDate") String leaveStartDate,
        @RequestParam("leaveEndDate") String leaveEndDate,
        @RequestParam("reason") String reason,
        @RequestParam("supervisorName") String supervisorName,
        @RequestParam("approvalStatus") String approvalStatus,
        @RequestParam("comments") String comments
    ) {
        // Define the date format you expect
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Convert String to LocalDate
        LocalDate startDate = LocalDate.parse(leaveStartDate, formatter);
        LocalDate endDate = LocalDate.parse(leaveEndDate, formatter);

        LeaveRequest leaveRequest = leaveRequestService.createLeaveRequest(name, employeeId, position, contactNumber,
                emailAddress, leaveType, startDate, endDate, reason, supervisorName,
                approvalStatus, comments);
        return new ResponseEntity<>(leaveRequest, HttpStatus.CREATED);
    }
}
