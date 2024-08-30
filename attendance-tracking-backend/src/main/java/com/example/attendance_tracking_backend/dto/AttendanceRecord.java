package com.example.attendance_tracking_backend.dto;

public class AttendanceRecord {

    private String empId;
    private String timeIn;
    private String timeOut;
    private String totalHours;

    public AttendanceRecord(String empId, String timeIn, String timeOut, String totalHours) {
        this.empId = empId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.totalHours = totalHours;
    }

    // Getters and setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }
}

