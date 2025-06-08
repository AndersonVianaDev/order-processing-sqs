package com.anderson.order_api.infra.aws.producer;

import com.anderson.order_api.domain.model.Order;
import com.anderson.order_api.domain.services.IMessagePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SqsMessagePublisher implements IMessagePublisher {

    @Value("${aws.queue.order-worker}")
    private String ORDER_WORKER_QUEUE;

    private final SqsTemplate sqsTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void send(Order order) {
        try {
            final String orderJSON = objectMapper.writeValueAsString(order);
            sqsTemplate.send(ORDER_WORKER_QUEUE, orderJSON);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
