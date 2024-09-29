package com.easyhostel.backend.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0.1/manager")
@Tag(name = "Manager Controller")
public class ManagerController {

    @GetMapping
    @Operation(summary = "Get manager's information", description = "")
    public String getManager() {
        return "Hello World";
    }

}
