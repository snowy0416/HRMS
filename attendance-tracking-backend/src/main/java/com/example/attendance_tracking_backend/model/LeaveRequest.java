package com.example.attendance_tracking_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String employeeId;
    private String position;
    private String contactNumber;
    private String emailAddress;
    private String leaveType;
    private LocalDate leaveStartDate;
    private LocalDate leaveEndDate;
    private String reason;
    private String supervisorName;
    private String approvalStatus;
    private String comments;

    // Default constructor (required by JPA)
    public LeaveRequest() {
    }

    // Constructor with all fields
    public LeaveRequest(String name, String employeeId, String position, String contactNumber,
                        String emailAddress, String leaveType, LocalDate leaveStartDate,
                        LocalDate leaveEndDate, String reason, String supervisorName,
                        String approvalStatus, String comments) {
        this.name = name;
        this.employeeId = employeeId;
        this.position = position;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.leaveType = leaveType;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.reason = reason;
        this.supervisorName = supervisorName;
        this.approvalStatus = approvalStatus;
        this.comments = comments;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(LocalDate leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public LocalDate getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(LocalDate leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
