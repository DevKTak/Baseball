package com.baseball.common.error.api;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class Error {

    private final String code;
    private final String message;
    private final Map<String, Object> properties;

    public Error(String code, String message, Map<String, Object> properties) {
        this.code = code;
        this.message = message;
        this.properties = properties == null ? Collections.emptyMap() : properties;
    }
}

