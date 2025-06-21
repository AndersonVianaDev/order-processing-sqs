package com.anderson.order_worker.domain.service.impl;

import com.anderson.order_worker.domain.model.Order;
import com.anderson.order_worker.domain.model.OrderItem;
import com.anderson.order_worker.domain.model.Product;
import com.anderson.order_worker.domain.model.enums.OrderStatus;
import com.anderson.order_worker.domain.service.IOrderProcessor;
import com.anderson.order_worker.domain.service.IProductService;
import com.anderson.order_worker.infra.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProcessorImpl implements IOrderProcessor {

    private final OrderRepository orderRepository;
    private final IProductService productService;

    @Override
    public void process(UUID orderId) {
        final Order order = orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        for (OrderItem item : order.getItems()) {
            final Product product = productService.findById(item.getProductId(), order.getOwnerId());

            if (product.getStockQuantity() < item.getQuantity()) {
                order.setStatus(OrderStatus.CANCELED_OUT_OF_STOCK);
                orderRepository.save(order);
                return;
            }

            productService.decreaseStock(product.getId(), order.getOwnerId(), item.getQuantity());
        }

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }
}
