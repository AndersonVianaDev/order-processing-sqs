package com.anderson.order_worker.infra.client;

import com.anderson.order_worker.domain.model.Product;
import com.anderson.order_worker.infra.dtos.DecreaseStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "product-service", url = "${api.url.product-api}")
public interface ProductClient {

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") UUID id);

    @PostMapping("/{id}/decrease-stock")
    void decreaseStock(@PathVariable("id") UUID id,
                       @RequestBody DecreaseStockDTO dto);
}
