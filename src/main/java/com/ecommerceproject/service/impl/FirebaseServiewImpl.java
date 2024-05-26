package com.ecommerceproject.service.impl;

import com.ecommerceproject.dto.ImageDTO;
import com.ecommerceproject.service.FirebaseSerivce;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirebaseServiewImpl implements FirebaseSerivce {

    @Value("classpath:firebase.json")
    private Resource firebaseCredential;

    @Value("${firebase.storage.bucket}")
    private String bucketName;

    @Value("${firebase.storage.urlFormat}")
    private String urlFormat;

    public List<ImageDTO> uploadFile(List<MultipartFile> files, String type) throws IOException {
        List<ImageDTO> fileList = new ArrayList<>();
        for(MultipartFile file: files) {
            InputStream serviceAccount = firebaseCredential.getInputStream();
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            BlobId blobId = BlobId.of(bucketName, type + "/" + file.getOriginalFilename());
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
            storage.create(blobInfo, file.getBytes());
            fileList.add(new ImageDTO(String.format(urlFormat, bucketName, type + "%2F", file.getOriginalFilename())));
        }
        return fileList;
    }
}
