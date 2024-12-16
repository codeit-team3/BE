package com.codeit.sprint.team3.backend.common.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadPort {
    String uploadImageToS3(MultipartFile file, String path, String fileName, String extension);
}
