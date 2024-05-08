package com.example.ecommerceproject.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    ByteArrayResource readContentFile(String imageId) throws IOException;

    String saveFile(MultipartFile file) throws IOException;
}
