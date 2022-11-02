package com.example.carreservation.domain.message;

import org.jetbrains.annotations.PropertyKey;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.Locale;
import java.util.ResourceBundle;

public class AppErrorMessages {
    public static final String BUNDLE_FQN = "ErrorMessages";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_FQN, new Locale("en", "US"));

    public static String message(@PropertyKey(resourceBundle = BUNDLE_FQN) String key, Object... params) {
        if (RESOURCE_BUNDLE.containsKey(key)) {
            String value = RESOURCE_BUNDLE.getString(key);
            final FormattingTuple tuple = MessageFormatter.arrayFormat(value, params);
            System.out.println(tuple.getMessage());
            return tuple.getMessage();
        } else {
            return MessageFormatter.arrayFormat(key, params).getMessage();
        }
    }
}
