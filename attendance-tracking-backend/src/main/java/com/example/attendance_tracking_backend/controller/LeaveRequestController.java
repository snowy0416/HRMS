package com.example.attendance_tracking_backend.controller;

import com.example.attendance_tracking_backend.model.LeaveRequest;
import com.example.attendance_tracking_backend.service.LeaveRequestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React app
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(leaveStartDate, formatter);
        LocalDate endDate = LocalDate.parse(leaveEndDate, formatter);

        LeaveRequest leaveRequest = leaveRequestService.createLeaveRequest(name, employeeId, position, contactNumber,
                emailAddress, leaveType, startDate, endDate, reason, supervisorName,
                approvalStatus, comments);
        return new ResponseEntity<>(leaveRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequestStatus(
        @PathVariable Long id,
        @RequestBody Map<String, String> updates
    ) {
        String approvalStatus = updates.get("approvalStatus");
        LeaveRequest updatedLeaveRequest = leaveRequestService.updateLeaveRequestStatus(id, approvalStatus);
        return new ResponseEntity<>(updatedLeaveRequest, HttpStatus.OK);
    }
}
