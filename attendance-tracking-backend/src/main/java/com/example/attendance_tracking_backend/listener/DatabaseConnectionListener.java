package com.example.attendance_tracking_backend.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionListener implements ApplicationListener<ApplicationReadyEvent> {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseConnectionListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("The application is fully started and the database is connected.");
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}

