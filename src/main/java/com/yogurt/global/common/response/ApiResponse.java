package com.yogurt.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private boolean success;
    private String message;
    private Object data;
    private Meta meta;

    public ApiResponse(boolean success, String message, Object data, Meta meta) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.meta = meta;
    }

    public static ApiResponse createSuccessApiResponse(String message, Object data, Meta meta) {
        return new ApiResponse(true, message, data, meta);
    }

    public static ApiResponse createApiResponse(boolean success, String message, Object data) {
        return new ApiResponse(success, message, data, null);
    }

    public static ApiResponse createApiResponse(boolean success, String message) {
        return createApiResponse(success, message, null);
    }

    public static ApiResponse createSuccessApiResponse(String message, Object data) {
        return new ApiResponse(true, message, data, null);
    }

    public static ApiResponse createSuccessApiResponse(String message) {
        return new ApiResponse(true, message, null, null);
    }

}
