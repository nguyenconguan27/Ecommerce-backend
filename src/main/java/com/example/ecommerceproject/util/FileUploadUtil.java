package com.example.ecommerceproject.util;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUploadUtil {
    public static Path save(MultipartFile file, String uploadDir) throws IOException {
        Path path = Paths.get(uploadDir);
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String nameGenerated = UUID.randomUUID().toString().replace("-", "");
        nameGenerated += "." + fileExtension;
        InputStream inputStream = file.getInputStream();
        Path pathFile = path.resolve(nameGenerated.toLowerCase());
        Files.copy(inputStream, pathFile, StandardCopyOption.REPLACE_EXISTING);
        return pathFile;
    }
}