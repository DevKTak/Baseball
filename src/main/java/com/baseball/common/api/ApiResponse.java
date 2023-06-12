package com.baseball.common.api;

import com.baseball.common.error.api.Error;

import java.util.Collections;
import java.util.Map;

public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final Error error;

    public ApiResponse(Boolean success, T data, Error error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public Error getError() {
        return error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                data,
                null
        );
    }

    public static <T> ApiResponse<T> fail(String code) {
        return new ApiResponse<>(
                false,
                null,
                new Error(
                        code,
                        null,
                        Collections.emptyMap()
                )
        );
    }

    public static <T> ApiResponse<T> fail(String code, String message) {
        return new ApiResponse<>(
                false,
                null,
                new Error(
                        code,
                        message,
                        Collections.emptyMap()
                )
        );
    }

    public static <T> ApiResponse<T> fail(String code, String message, Map<String, Object> properties) {
        return new ApiResponse<>(
                false,
                null,
                new Error(
                        code,
                        message,
                        properties
                )
        );
    }
}
