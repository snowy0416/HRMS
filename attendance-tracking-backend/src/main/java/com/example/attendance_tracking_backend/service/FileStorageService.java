package com.example.attendance_tracking_backend.service; // Adjust the package as needed

import com.example.attendance_tracking_backend.config.FileStorageConfig; // Correctly import the configuration class
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;

@Service
public class FileStorageService {

    private final FileStorageConfig fileStorageConfig;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageConfig = fileStorageConfig;
    }

    @PostConstruct
    public void init() {
        File directory = new File(fileStorageConfig.getUploadDir());
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // Other methods for file handling...
}
