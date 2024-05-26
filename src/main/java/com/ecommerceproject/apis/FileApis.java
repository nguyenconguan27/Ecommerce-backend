package com.ecommerceproject.apis;

import com.ecommerceproject.dto.ImageDTO;
import com.ecommerceproject.payload.response.ResponseObject;
import com.ecommerceproject.service.FileService;
import com.ecommerceproject.service.FirebaseSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileApis {

    @Autowired
    private FileService fileService;
    @Autowired
    private FirebaseSerivce firebaseSerivce;

    @GetMapping("/")
    public ResponseEntity<ByteArrayResource> getImage(@RequestParam(name = "image_id") String imageId) throws IOException {
        ByteArrayResource byteArrayResource = fileService.readContentFile(imageId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(byteArrayResource);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String url = fileService.saveFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Save image successfully")
                        .data(url)
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }

    @PostMapping("/upload/{type}")
    public ResponseEntity<ResponseObject> uploadImage1(@RequestBody List<MultipartFile> files, @PathVariable String type) throws IOException {
        List<ImageDTO> urls = firebaseSerivce.uploadFile(files, type);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Save image successfully")
                        .data(urls)
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }
}
