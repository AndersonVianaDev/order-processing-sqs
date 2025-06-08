package com.anderson.order_api.infra.client;

import com.anderson.order_api.domain.model.Product;
import com.anderson.order_api.infra.client.dtos.DecreaseStockRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "productClient", url = "localhost:8080/products")
public interface ProductClient {

    @GetMapping("/{id}")
    Product getProductById(@RequestHeader("X-USER-ID") UUID userId,
                           @PathVariable("id") UUID id);

    @PostMapping("/{id}/decrease-stock")
    Product decreaseStock(@RequestHeader("X-USER-ID") UUID userId,
                          @PathVariable("id") UUID id,
                          @RequestBody DecreaseStockRequestDTO request);

}
