package com.baseball.common;

import java.util.Collections;
import java.util.Map;

public class Error {
    private final String code;
    private final String message;
    private final Map<String, Object> properties;

    public Error(String code, String message, Map<String, Object> properties) {
        this.code = code;
        this.message = message;
        this.properties = properties == null ? Collections.emptyMap() : properties;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}

