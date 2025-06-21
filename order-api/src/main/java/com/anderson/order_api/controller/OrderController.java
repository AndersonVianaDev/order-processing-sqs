package com.anderson.order_api.controller;

import com.anderson.order_api.controller.dtos.request.OrderRequestDTO;
import com.anderson.order_api.controller.dtos.response.OrderResponseDTO;
import com.anderson.order_api.domain.model.Order;
import com.anderson.order_api.domain.services.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> save(@RequestBody @Valid OrderRequestDTO request) {
        final Order order = service.save(OrderRequestDTO.from(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponseDTO.of(order));
    }
}
