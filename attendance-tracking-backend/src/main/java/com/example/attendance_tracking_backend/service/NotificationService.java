package com.example.attendance_tracking_backend.service;

import com.example.attendance_tracking_backend.model.Notification;
import com.example.attendance_tracking_backend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}


