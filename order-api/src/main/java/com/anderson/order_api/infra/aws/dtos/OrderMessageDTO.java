package com.anderson.order_api.infra.aws.dtos;

import com.anderson.order_api.domain.model.Order;

import java.util.UUID;

public record OrderMessageDTO(UUID orderId) {
    public static OrderMessageDTO of(Order order) {
        return new OrderMessageDTO(order.getId());
    }
}
