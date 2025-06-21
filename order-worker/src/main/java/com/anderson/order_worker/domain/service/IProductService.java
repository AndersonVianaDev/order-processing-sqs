package com.anderson.order_worker.domain.service;

import com.anderson.order_worker.domain.model.Product;

import java.util.UUID;

public interface IProductService {

    Product findById(UUID productId, UUID userId);
    void decreaseStock(UUID productId, UUID userId, Integer quantity);
}
