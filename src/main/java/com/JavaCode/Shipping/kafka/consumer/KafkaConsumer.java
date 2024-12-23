package com.JavaCode.Shipping.kafka.consumer;


import com.JavaCode.Shipping.dto.OrderDto;
import com.JavaCode.Shipping.service.ShippingService;
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
    public final ShippingService shippingService;

    public KafkaConsumer(KafkaTemplate<String, OrderDto> kafkaTemplate, ShippingService shippingService) {
        this.kafkaTemplate = kafkaTemplate;
        this.shippingService = shippingService;
    }

    @RetryableTopic(
        attempts = "3",
        backoff = @Backoff(delay = 2000, multiplier = 2),
        dltTopicSuffix = ".dlt"
    )
    @KafkaListener(topics = "payed_orders", groupId = "shipping-group")
    public void listenOrder(OrderDto orderDto) {
        logger.info(">>>Поступление нового заказа с ID: {}", orderDto.getOrderId());
        try {
            kafkaTemplate.send("sent_orders", shippingService.productPackaging(orderDto));
            logger.info(">>> Заказ  отгружен на склад  ID: {}", orderDto.getOrderId());
        } catch (Exception exception) {
            logger.warn(">>> Сбой при отправке   ID: {}", orderDto.getOrderId());
            logger.warn(">>> Error massage: {}", exception.getMessage());
        }

    }
}
