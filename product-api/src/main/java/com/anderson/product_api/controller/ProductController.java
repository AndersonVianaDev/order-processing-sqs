package com.anderson.product_api.controller;

import com.anderson.product_api.controller.dtos.request.ProductRequestDTO;
import com.anderson.product_api.controller.dtos.response.ProductResponseDTO;
import com.anderson.product_api.domain.model.Product;
import com.anderson.product_api.domain.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestHeader("X-USER-ID") UUID userId,
                                                   @RequestBody @Valid ProductRequestDTO request) {
        final Product product = service.save(ProductRequestDTO.of(userId, request));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.from(product));
    }
}
