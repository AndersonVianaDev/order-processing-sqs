package com.anderson.order_worker.domain.service.impl;

import com.anderson.order_worker.domain.model.Order;
import com.anderson.order_worker.domain.model.OrderItem;
import com.anderson.order_worker.domain.model.Product;
import com.anderson.order_worker.domain.model.enums.OrderStatus;
import com.anderson.order_worker.domain.service.IOrderProcessor;
import com.anderson.order_worker.domain.service.IProductGateway;
import com.anderson.order_worker.infra.exceptions.InvalidStockOperationException;
import com.anderson.order_worker.infra.exceptions.NotFoundException;
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
    private final IProductGateway productGateway;

    @Override
    public void process(UUID orderId) {
        final Order order = orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if(order.getStatus() != OrderStatus.PENDING) {
            return;
        }

        try {
            for (OrderItem item : order.getItems()) {
                productGateway.decreaseStock(item.getProductId(), item.getQuantity());
            }

            order.setStatus(OrderStatus.CONFIRMED);

        } catch (NotFoundException e) {
            order.setStatus(OrderStatus.CANCELED);
        } catch (InvalidStockOperationException e) {
            order.setStatus(OrderStatus.CANCELED_OUT_OF_STOCK);
        } catch (Exception e) {
            order.setStatus(OrderStatus.CANCELED);
        } finally {
            orderRepository.save(order);
        }
    }
}
