package com.easyhostel.backend.api.controller;

import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.response.FormatedResponse;
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
    public FormatedResponse<Integer> getManager() {
        return new FormatedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("manager.read.success"),
                null
        );
    }

    @PostMapping
    public FormatedResponse<String> addManager() {
        return new FormatedResponse<>(
                true,
                HttpStatus.CREATED.value(),
                null,
                Translator.toLocale("manager.create.success"),
                null
        );
    }

    @PutMapping
    public FormatedResponse<Integer> updateManager() {
        return new FormatedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("manager.update.success"),
                null
        );    }

    @DeleteMapping
    public FormatedResponse<Integer> deleteManager() {
        return new FormatedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("manager.delete.success"),
                null
        );    }

}
