package com.attornatus.application.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {

    private String message;
    private Integer statusCode;
    private LocalDateTime timestamp;

}
