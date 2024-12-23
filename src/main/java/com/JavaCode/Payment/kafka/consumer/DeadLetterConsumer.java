package com.JavaCode.Payment.kafka.consumer;

import com.JavaCode.Payment.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterConsumer {
    private static final Logger logger = LoggerFactory.getLogger(DeadLetterConsumer.class);

    @KafkaListener(topics = "new_orders.dlt", groupId = "payment-group")
    public void listenDeadLetter(OrderDto orderDto) {
        logger.info(">>> Сообщение сохранено в БД ID: {}", orderDto.getOrderId());
    }
}