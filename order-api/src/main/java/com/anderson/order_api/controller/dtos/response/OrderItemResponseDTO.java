package com.anderson.order_api.controller.dtos.response;

import com.anderson.order_api.domain.model.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDTO(
        UUID id,
        UUID productId,
        String productName,
        BigDecimal productPrice,
        BigDecimal total,
        int quantity
) {
    public static OrderItemResponseDTO of(OrderItem item) {
        return new OrderItemResponseDTO(
                item.getId(),
                item.getProductId(),
                item.getProductName(),
                item.getProductPrice(),
                item.getTotal(),
                item.getQuantity()
        );
    }
}
