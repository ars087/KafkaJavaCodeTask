package com.JavaCode.Notifications.kafka.consumer;


import com.JavaCode.Notifications.dto.OrderDto;
import com.JavaCode.Notifications.dto.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterConsumer {
    private static final Logger logger = LoggerFactory.getLogger(DeadLetterConsumer.class);

    @KafkaListener(topics = "sent_orders.dlt", groupId = "notification-group")
    public void listenDeadLetter(OrderDto orderDto) {
        orderDto.setStatus(OrderStatus.DELIVERY);
        logger.info(">>> Сообщение сохранено в БД ID: {}", orderDto.getOrderId());
    }
}