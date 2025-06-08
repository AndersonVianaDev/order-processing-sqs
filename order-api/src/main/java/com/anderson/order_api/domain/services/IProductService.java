package com.anderson.order_api.domain.services;

import com.anderson.order_api.domain.model.Product;

import java.util.UUID;

public interface IProductService {
    Product findById(UUID ownerId, UUID id);
}
