package com.anderson.product_api.domain.service.impl;

import com.anderson.product_api.domain.model.Product;
import com.anderson.product_api.domain.service.IProductService;
import com.anderson.product_api.infra.exception.DataConflictException;
import com.anderson.product_api.infra.exception.InvalidStockOperationException;
import com.anderson.product_api.infra.exception.NotFoundException;
import com.anderson.product_api.infra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
        if (repository.findByName(product.getName()).isPresent()) {
            throw new DataConflictException("product already registered");
        }

        return repository.save(product);
    }

    @Override
    public Product findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product decreaseStock(UUID id, int quantity) {
        final Product product = findById(id);
        int newStock = product.getStockQuantity() - quantity;

        if(newStock < 0) {
            throw new InvalidStockOperationException("Insufficient stock for this operation");
        }

        product.setStockQuantity(newStock);

        return repository.save(product);
    }
}
