package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.domain.enums.ErrorCode;

import java.io.FileWriter;
import java.io.IOException;

public class ErrorCodeDocumentationGenerator {

    public static void main(String[] args) throws IOException {
        generateErrorCodeDocumentation();
    }

    private static void generateErrorCodeDocumentation() throws IOException {
        try (FileWriter writer = new FileWriter("ERROR_CODES.md")) {
            writer.write("# Error Code Reference\n\n");
            writer.write("| Error Code                     | HTTP Status | Description                                        |\n");
            writer.write("|--------------------------------|-------------|----------------------------------------------------|\n");

            for (ErrorCode errorCode : ErrorCode.values()) {
                String row = String.format("| %-30s | %-11s | %-50s |\n",
                        errorCode.getCode(), getHttpStatus(errorCode), errorCode.getMessage());
                writer.write(row);
            }
        }
    }

    private static String getHttpStatus(ErrorCode errorCode) {
        return switch (errorCode) {
            case RESOURCE_NOT_FOUND -> "404";
            case VALIDATION_ERROR -> "400";
            case UNAUTHORIZED_ACCESS -> "403";
            case INTERNAL_SERVER_ERROR -> "500";
            case BUSINESS_LOGIC_ERROR -> "422";
            default -> "500";
        };
    }
}

