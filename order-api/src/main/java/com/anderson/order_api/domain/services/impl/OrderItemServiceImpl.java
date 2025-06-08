package com.anderson.order_api.domain.services.impl;

import com.anderson.order_api.domain.model.Order;
import com.anderson.order_api.domain.model.OrderItem;
import com.anderson.order_api.domain.model.Product;
import com.anderson.order_api.domain.services.IOrderItemService;
import com.anderson.order_api.domain.services.IProductService;
import com.anderson.order_api.infra.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {

    private final OrderItemRepository repository;
    private final IProductService productService;

    @Override
    public List<OrderItem> save(List<OrderItem> orderItems, Order order) {
        for (OrderItem item : orderItems) {
            Product product = productService.findById(order.getOwnerId(), item.getProductId());

            item.setProductName(product.getName());
            item.setProductPrice(product.getPrice());
            item.setTotal(item.calculateSubtotal());
            item.setOrder(order);
        }

        return repository.saveAll(orderItems);
    }
}
