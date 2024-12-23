package com.JavaCode.Shipping.kafka.consumer;

import com.JavaCode.Shipping.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterConsumer {
    private static final Logger logger = LoggerFactory.getLogger(DeadLetterConsumer.class);

    @KafkaListener(topics = "payed_orders.dlt", groupId = "order-group")
    public void listenDeadLetter(OrderDto orderDto) {
        logger.info(">>> Сообщение сохранено в БД ID: {}", orderDto.getOrderId());

    }
}