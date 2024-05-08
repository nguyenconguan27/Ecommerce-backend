package com.example.ecommerceproject.service;

import com.example.ecommerceproject.dto.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FirebaseSerivce {
    List<ImageDTO> uploadFile(List<MultipartFile> files, String type) throws IOException;
}
