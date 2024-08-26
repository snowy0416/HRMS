package com.example.attendance_tracking_backend.service;


import com.example.attendance_tracking_backend.model.UserProfile;
import com.example.attendance_tracking_backend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.time.LocalDate;


@Service
public class UserProfileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public Optional<UserProfile> getProfile(String empId) {
        return userProfileRepository.findByEmpId(empId);
    }

    public UserProfile updateProfile(String name, String empId, String designation, String dateOfJoining, String phone, String email, MultipartFile photoFile) throws IOException {
        Optional<UserProfile> optionalProfile = userProfileRepository.findByEmpId(empId);
        if (!optionalProfile.isPresent()) {
            throw new RuntimeException("Profile not found");
        }

        UserProfile profile = optionalProfile.get();
        profile.setName(name);
        profile.setDesignation(designation);
        profile.setDateOfJoining(LocalDate.parse(dateOfJoining));
        profile.setPhone(phone);
        profile.setEmail(email);

        if (photoFile != null && !photoFile.isEmpty()) {
            String photoUrl = saveFile(photoFile);
            profile.setPhotoUrl(photoUrl);
        }

        return userProfileRepository.save(profile);
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }
}
