package com.ecommerceproject.apis;

import com.ecommerceproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserApis {
    @Autowired
    private UserService userService;
}
