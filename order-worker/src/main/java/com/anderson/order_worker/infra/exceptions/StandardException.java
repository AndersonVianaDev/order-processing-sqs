package com.anderson.order_worker.infra.exceptions;

import java.time.LocalDateTime;

public record StandardException(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
