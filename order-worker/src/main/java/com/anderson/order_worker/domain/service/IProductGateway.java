package com.anderson.order_worker.domain.service;

import java.util.UUID;

public interface IProductGateway {

    void decreaseStock(UUID productId, Integer quantity);
}
