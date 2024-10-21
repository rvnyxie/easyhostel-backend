package com.easyhostel.backend.infrastructure.util.custom.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class FormattedResponse<T> {

    private boolean success;

    private int statusCode;

    private String errorCode;

    private String message;

    private T data;

    private final LocalDateTime timestamp = LocalDateTime.now();

}
