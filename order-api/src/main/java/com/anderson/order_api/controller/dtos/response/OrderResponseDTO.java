package com.anderson.order_api.controller.dtos.response;

import com.anderson.order_api.domain.model.Order;
import com.anderson.order_api.domain.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        UUID ownerId,
        OrderStatus status,
        BigDecimal total,
        List<OrderItemResponseDTO> orderItems,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static OrderResponseDTO of(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getOwnerId(),
                order.getStatus(),
                order.getTotal(),
                order.getItems().isEmpty() ?
                        List.of() :
                        order.getItems()
                                .stream()
                                .map(OrderItemResponseDTO::of)
                                .toList(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
