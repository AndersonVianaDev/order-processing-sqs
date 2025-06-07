package com.anderson.product_api.controller.dtos.response;

import com.anderson.product_api.domain.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        Boolean disabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ProductResponseDTO of(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getDisabled(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}

