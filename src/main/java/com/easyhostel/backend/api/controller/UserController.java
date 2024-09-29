package com.easyhostel.backend.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0.1/user")
public class UserController {

    @GetMapping
    public String getUser() {
        return "Hello World";
    }

}
