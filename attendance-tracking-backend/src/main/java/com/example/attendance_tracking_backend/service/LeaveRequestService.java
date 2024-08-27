package com.example.attendance_tracking_backend.service;

import com.example.attendance_tracking_backend.model.LeaveRequest;
import com.example.attendance_tracking_backend.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setName(name);
        leaveRequest.setEmployeeId(employeeId);
        leaveRequest.setPosition(position);
        leaveRequest.setContactNumber(contactNumber);
        leaveRequest.setEmailAddress(emailAddress);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setLeaveStartDate(leaveStartDate);
        leaveRequest.setLeaveEndDate(leaveEndDate);
        leaveRequest.setReason(reason);
        leaveRequest.setSupervisorName(supervisorName);
        leaveRequest.setApprovalStatus(approvalStatus);
        leaveRequest.setComments(comments);


        return leaveRequestRepository.save(leaveRequest);
    }
}
