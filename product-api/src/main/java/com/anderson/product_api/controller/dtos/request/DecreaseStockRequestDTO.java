package com.anderson.product_api.controller.dtos.request;

import jakarta.validation.constraints.NotNull;

public record DecreaseStockRequestDTO(
        @NotNull(message = "'quantity' is required")
        int quantity
) {
}
