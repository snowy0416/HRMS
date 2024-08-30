package com.example.attendance_tracking_backend.service;

import com.example.attendance_tracking_backend.model.LeaveRequest;
import com.example.attendance_tracking_backend.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;      // Import LocalDate
import java.util.List;           // Import List
import java.util.Optional;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    public LeaveRequest createLeaveRequest(String name, String employeeId, String position, String contactNumber,
                                           String emailAddress, String leaveType, LocalDate leaveStartDate, 
                                           LocalDate leaveEndDate, String reason, String supervisorName,
                                           String approvalStatus, String comments) {
        LeaveRequest leaveRequest = new LeaveRequest(name, employeeId, position, contactNumber, emailAddress,
                                                     leaveType, leaveStartDate, leaveEndDate, reason, 
                                                     supervisorName, approvalStatus, comments);
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest updateLeaveRequestStatus(Long id, String approvalStatus) {
        Optional<LeaveRequest> optionalLeaveRequest = leaveRequestRepository.findById(id);
        
        if (optionalLeaveRequest.isPresent()) {
            LeaveRequest leaveRequest = optionalLeaveRequest.get();
            leaveRequest.setApprovalStatus(approvalStatus);
            return leaveRequestRepository.save(leaveRequest);
        } else {
            throw new IllegalArgumentException("Leave request not found with ID: " + id);
        }
    }
}
