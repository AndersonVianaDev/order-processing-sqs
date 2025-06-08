package com.anderson.order_api.domain.services;

import com.anderson.order_api.domain.model.Order;

public interface IOrderService {
    Order save(Order order);
}
