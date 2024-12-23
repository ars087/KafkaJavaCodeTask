package com.JavaCode.Orders.service;

import com.JavaCode.Orders.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class OrderProducer {
    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private static final String TOPIC = "new_orders";

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean sendOrder(OrderDto orderDto) {

        try {
            kafkaTemplate.send(TOPIC, orderDto).get();
            logger.info(">>> Новый заказ принят и  отправлен в дальнейшую обработку ID: {}", orderDto.getOrderId());
            return true;
        } catch (Exception e) {
            logger.warn(">>> Произошел сбой при обработке заказа ID: {}", orderDto.getOrderId());
            return false;
        }

    }
}
