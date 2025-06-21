package com.anderson.order_api.controller.dtos.request;

import com.anderson.order_api.domain.model.Order;

import java.util.List;

public record OrderRequestDTO(
        List<OrderItemRequestDTO> orderItems
) {
    public static Order from(OrderRequestDTO request) {
        return Order.builder()
                .items(request.orderItems().stream().map(OrderItemRequestDTO::from).toList())
                .build();
    }
}
