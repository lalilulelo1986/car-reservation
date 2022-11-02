package com.example.carreservation.domain.exception;

public class ReservationException extends LocalizedRuntimeException {
    public ReservationException(String message, Object... params) {
        super(message, params);
    }
}
