package com.anderson.order_worker.infra.services;

import com.anderson.order_worker.domain.model.Product;
import com.anderson.order_worker.domain.service.IProductGateway;
import com.anderson.order_worker.infra.client.ProductClient;
import com.anderson.order_worker.infra.dtos.DecreaseStockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductGatewayImpl implements IProductGateway {

    private final ProductClient productClient;

    @Override
    public void decreaseStock(UUID productId, Integer quantity) {
        productClient.decreaseStock(productId, DecreaseStockDTO.from(quantity));
    }
}
