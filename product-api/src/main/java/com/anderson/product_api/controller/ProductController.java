package com.anderson.product_api.controller;

import com.anderson.product_api.controller.dtos.request.ProductRequestDTO;
import com.anderson.product_api.controller.dtos.response.PageResponseDTO;
import com.anderson.product_api.controller.dtos.response.ProductResponseDTO;
import com.anderson.product_api.domain.model.Product;
import com.anderson.product_api.domain.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        final Product product = service.save(ProductRequestDTO.from(userId, request));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.of(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@RequestHeader("X-USER-ID") UUID userId,
                                                       @PathVariable("id") UUID id) {
        final Product product = service.findById(userId, id);

        return ResponseEntity.ok(ProductResponseDTO.of(product));
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> findAll(@RequestHeader("X-USER-ID") UUID userId,
                                                                        Pageable pageable) {
        final Page<ProductResponseDTO> page = service.findAll(userId, pageable)
                .map(ProductResponseDTO::of);

        return ResponseEntity.ok(PageResponseDTO.of(page));
    }
}
