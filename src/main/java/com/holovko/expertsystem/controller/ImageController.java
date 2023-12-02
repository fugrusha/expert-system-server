package com.holovko.expertsystem.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private static final String UPLOAD_FOLDER_NAME = "/Users/aholovko/Documents/University/Магістратура 122 Комп науки/Экспертные системы/курсовая/source-code/expertsystem-ui/expert-system-frontend/src/assets";

    @PostMapping("/upload")
    public ResponseEntity<ImageUrl> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("upload image={}", file.getOriginalFilename());
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Create the folder if it doesn't exist
        Path uploadPath = Path.of(UPLOAD_FOLDER_NAME);
        Files.createDirectories(uploadPath);

        // Save the file to the folder
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return ResponseEntity.ok(new ImageUrl(filename));
    }

    @Data
    private static class ImageUrl {

        private final String url;
    }
}
