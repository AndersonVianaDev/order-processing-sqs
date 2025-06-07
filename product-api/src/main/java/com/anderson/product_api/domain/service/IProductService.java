package com.anderson.product_api.domain.service;

import com.anderson.product_api.domain.model.Product;

import java.util.UUID;

public interface IProductService {
    Product save(Product product);
    Product findById(UUID ownerId, UUID id);
}
