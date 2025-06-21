package com.anderson.order_worker.domain.service;

import com.anderson.order_worker.domain.model.Order;

import java.util.UUID;

public interface IOrderProcessor {
    void process(UUID orderId);
}
