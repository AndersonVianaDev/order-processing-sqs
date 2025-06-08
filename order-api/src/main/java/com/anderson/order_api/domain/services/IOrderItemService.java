package com.anderson.order_api.domain.services;

import com.anderson.order_api.domain.model.Order;
import com.anderson.order_api.domain.model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> save(List<OrderItem> orderItems, Order order);
}
