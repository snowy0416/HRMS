package com.example.attendance_tracking_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String startTime;
    private String endTime;

   public Shift() {
}

// Parameterized constructor
public Shift(String name, String startTime, String endTime) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
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

public String getStartTime() {
    return startTime;
}

public void setStartTime(String startTime) {
    this.startTime = startTime;
}

public String getEndTime() {
    return endTime;
}

public void setEndTime(String endTime) {
    this.endTime = endTime;
}
}

