package com.anderson.product_api.domain.service;

import com.anderson.product_api.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IProductService {
    Product save(Product product);
    Product findById(UUID id);
    Page<Product> findAll(Pageable pageable);
    Product decreaseStock(UUID id, int quantity);
}
