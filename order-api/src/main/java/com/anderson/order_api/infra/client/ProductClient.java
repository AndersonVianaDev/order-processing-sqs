package com.anderson.order_api.infra.client;

import com.anderson.order_api.domain.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "productClient", url = "${api.url.product-api}")
public interface ProductClient {

    @GetMapping("/{id}")
    Product getProductById(@PathVariable("id") UUID id);

}
