package com.easyhostel.backend.api.controller;

import com.easyhostel.backend.infrastructure.util.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0.1/manager")
@Tag(name = "Manager Controller")
public class ManagerController {

    @GetMapping
    @Operation(summary = "Get manager's information", description = "")
    public ApiResponse<Integer> getManager() {
        return new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                "GET",
                null
        );
    }

    @PostMapping
    public ApiResponse<String> addManager() {
        return new ApiResponse<>(
                true,
                HttpStatus.CREATED.value(),
                null,
                "POST",
                null
        );
    }

    @PutMapping
    public ApiResponse<Integer> updateManager() {
        return new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                "PUT",
                null
        );    }

    @DeleteMapping
    public ApiResponse<Integer> deleteManager() {
        return new ApiResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                "DELETE",
                null
        );    }

}
