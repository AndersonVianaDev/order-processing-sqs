package com.anderson.order_api.controller.dtos.request;

import com.anderson.order_api.domain.model.Order;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(
        List<OrderItemRequestDTO> orderItems
) {
    public static Order from(UUID userId, OrderRequestDTO request) {
        return Order.builder()
                .ownerId(userId)
                .items(request.orderItems().stream().map(OrderItemRequestDTO::from).toList())
                .build();
    }
}
