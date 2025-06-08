package com.anderson.order_api.controller.dtos.request;

import com.anderson.order_api.domain.model.OrderItem;

import java.util.UUID;

public record OrderItemRequestDTO(
        UUID productId,
        Integer quantity
) {
    public static OrderItem from(OrderItemRequestDTO request) {
        return OrderItem.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
    }
}
