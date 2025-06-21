package com.anderson.order_worker.infra.services;

import com.anderson.order_worker.domain.model.Product;
import com.anderson.order_worker.domain.service.IProductService;
import com.anderson.order_worker.infra.client.ProductClient;
import com.anderson.order_worker.infra.dtos.DecreaseStockDTO;
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
    public Product findById(UUID productId, UUID userId) {
        return productClient.getProductById(userId, productId);
    }

    @Override
    public void decreaseStock(UUID productId, UUID userId, Integer quantity) {
        productClient.decreaseStock(userId, productId, DecreaseStockDTO.from(quantity));
    }
}
