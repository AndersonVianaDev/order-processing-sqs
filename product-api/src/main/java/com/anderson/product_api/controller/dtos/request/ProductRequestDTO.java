package com.anderson.product_api.controller.dtos.request;

import com.anderson.product_api.domain.model.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDTO(
        @NotBlank(message = "'name' is required")
        String name,
        String description,

        @NotNull(message = "'price' is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        BigDecimal price,

        @NotNull(message = "'stock' is required")
        @Min(value = 0, message = "Stock quantity must be zero or greater")
        Integer stockQuantity) {

    public static Product from(UUID userId, ProductRequestDTO request) {
        return Product.builder()
                .ownerId(userId)
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .disabled(false)
                .build();
    }
}
