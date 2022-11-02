package com.example.carreservation.domain.exception;

public class ResourceNotFoundException extends LocalizedRuntimeException {
    public ResourceNotFoundException(String message, Object... params) {
        super(message, params);
    }
}
