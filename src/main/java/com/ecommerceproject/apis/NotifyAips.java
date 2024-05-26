package com.ecommerceproject.apis;

import com.ecommerceproject.service.NotifyService;
import com.ecommerceproject.payload.response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/notify")
public class NotifyAips {
    @Autowired
    private NotifyService notifyService;
    @GetMapping("/")
    public ResponseEntity<ResponseObject> getByUser(@RequestParam Integer userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Get notify successfully")
                        .data(notifyService.getByUser(userId))
                        .build()
        );
    }
}
