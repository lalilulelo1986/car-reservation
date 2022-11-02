package com.example.carreservation.domain.exception;

import com.example.carreservation.domain.message.AppErrorMessages;
import org.jetbrains.annotations.PropertyKey;

import static com.example.carreservation.domain.message.AppErrorMessages.BUNDLE_FQN;

public class LocalizedRuntimeException extends RuntimeException {
    final String messageCode;

    public LocalizedRuntimeException(@PropertyKey(resourceBundle = BUNDLE_FQN) String messageCode, Object... params) {
        super(AppErrorMessages.message(messageCode, params));
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
