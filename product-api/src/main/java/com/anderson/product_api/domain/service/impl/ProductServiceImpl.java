package com.anderson.product_api.domain.service.impl;

import com.anderson.product_api.domain.model.Product;
import com.anderson.product_api.domain.service.IProductService;
import com.anderson.product_api.infra.exception.DataConflictException;
import com.anderson.product_api.infra.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
        if (repository.findByOwnerIdAndName(product.getOwnerId(), product.getName()).isPresent()) {
            throw new DataConflictException("product already registered");
        }

        return repository.save(product);
    }
}
