package com.example.carreservation.controller.handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final HttpStatus status;
    private final String code;
    private final String messageCode;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;

    public ErrorResponse(HttpStatus status, String messageCode, String message, String path) {
        this.status = status;
        this.code = String.valueOf(status.value());
        this.messageCode = messageCode;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status, String message, String path) {
        this.status = status;
        this.code = String.valueOf(status.value());
        this.messageCode = null;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
