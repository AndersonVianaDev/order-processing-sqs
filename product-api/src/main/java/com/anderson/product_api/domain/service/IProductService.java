package com.anderson.product_api.domain.service;

import com.anderson.product_api.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProductService {
    Product save(Product product);
    Product findById(UUID ownerId, UUID id);
    Page<Product> findAll(UUID ownerId, Pageable pageable);
    Product decreaseStock(UUID ownerId, UUID id, int quantity);
}
