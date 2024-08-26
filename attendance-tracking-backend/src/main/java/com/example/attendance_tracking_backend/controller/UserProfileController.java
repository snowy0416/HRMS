package com.example.attendance_tracking_backend.controller;



import com.example.attendance_tracking_backend.model.UserProfile;
import com.example.attendance_tracking_backend.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{empId}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable String empId) {
        Optional<UserProfile> profile = userProfileService.getProfile(empId);
        return profile.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/update-profile")
    public ResponseEntity<UserProfile> updateProfile(
            @RequestParam("name") String name,
            @RequestParam("empId") String empId,
            @RequestParam("designation") String designation,
            @RequestParam("dateOfJoining") String dateOfJoining,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam(value = "photoFile", required = false) MultipartFile photoFile) {
        try {
            UserProfile updatedProfile = userProfileService.updateProfile(name, empId, designation, dateOfJoining, phone, email, photoFile);
            return ResponseEntity.ok(updatedProfile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}