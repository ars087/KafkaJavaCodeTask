package com.JavaCode.Notifications.kafka.consumer;


import com.JavaCode.Notifications.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    public final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public KafkaConsumer(KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RetryableTopic(
        attempts = "3",
        backoff = @Backoff(delay = 2000, multiplier = 2),
        dltTopicSuffix = ".dlt"
    )
    @KafkaListener(topics = "sent_orders", groupId = "notifications-group")
    public void listenOrder(OrderDto orderDto) {
        logger.info(">>>Заказ доставлен покупателю ID: {}", orderDto.getOrderId());

    }
}
