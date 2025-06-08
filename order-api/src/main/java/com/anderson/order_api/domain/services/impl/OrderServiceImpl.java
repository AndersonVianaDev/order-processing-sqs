package com.anderson.order_api.domain.services.impl;

import com.anderson.order_api.domain.model.Order;
import com.anderson.order_api.domain.model.OrderItem;
import com.anderson.order_api.domain.model.enums.OrderStatus;
import com.anderson.order_api.domain.services.IOrderItemService;
import com.anderson.order_api.domain.services.IOrderService;
import com.anderson.order_api.infra.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository repository;
    private final IOrderItemService orderItemService;

    @Override
    @Transactional
    public Order save(Order order) {
        order.setStatus(OrderStatus.CREATED);
        Order orderSaved = repository.save(order);

        List<OrderItem> orderItems = orderItemService.save(order.getItems(), orderSaved);
        orderSaved.setItems(orderItems);
        orderSaved.setTotal(orderSaved.calculateTotal());

        return orderSaved;
    }
}
