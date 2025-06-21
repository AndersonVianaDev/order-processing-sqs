package com.anderson.order_worker.infra.dtos;

import java.util.UUID;

public record OrderMessageDTO(UUID orderId) {
}
