package com.authentication.auth_service.testcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/secure/hello")
    public String helloSecure() {
        return "Hello! You accessed a secure endpoint ✅";
    }
}
