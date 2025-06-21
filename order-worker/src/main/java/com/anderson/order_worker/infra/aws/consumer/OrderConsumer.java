package com.anderson.order_worker.infra.aws.consumer;

import com.anderson.order_worker.domain.service.IOrderProcessor;
import com.anderson.order_worker.infra.dtos.OrderMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final SqsTemplate sqsTemplate;
    private final IOrderProcessor processor;

    @SqsListener("${aws.queue.order-worker}")
    public void consumer(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            OrderMessageDTO dto = mapper.readValue(message, OrderMessageDTO.class);

            processor.process(dto.orderId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
