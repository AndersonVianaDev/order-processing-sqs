package com.anderson.product_api.infra.exception;

import java.time.Instant;
import java.time.LocalDateTime;

public record StandardException(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {

    public static StandardException from(
            LocalDateTime timestamp,
            Integer status,
            String error,
            String message,
            String path
    ) {
        return new StandardException(timestamp, status, error, message, path);
    }
}
