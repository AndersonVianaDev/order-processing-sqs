package com.anderson.order_api.infra.services;

import com.anderson.order_api.domain.model.Product;
import com.anderson.order_api.domain.services.IProductService;
import com.anderson.order_api.infra.client.ProductClient;
import com.anderson.order_api.infra.client.dtos.DecreaseStockRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductClient productClient;

    @Override
    public Product findById(UUID ownerId, UUID id) {
        return productClient.getProductById(ownerId, id);
    }
}
