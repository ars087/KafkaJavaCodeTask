package com.JavaCode.Payment.kafka.consumer;

import com.JavaCode.Payment.dto.OrderDto;
import com.JavaCode.Payment.dto.OrderStatus;
import com.JavaCode.Payment.service.PaymentService;
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
    private final PaymentService paymentService;
    public final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public KafkaConsumer(PaymentService paymentService, KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.paymentService = paymentService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @RetryableTopic(
        attempts = "3",
        backoff = @Backoff(delay = 2000, multiplier = 2),
        dltTopicSuffix = ".dlt"
    )
    @KafkaListener(topics = "new_orders", groupId = "payment-group")
    public void listenOrder(OrderDto orderDto) {
        logger.info(">>>Поступление нового заказа с ID: {}", orderDto.getOrderId());
        logger.info(">>>Проверка заказа на оплпту с ID: {}", orderDto.getOrderId());
        if (paymentService.processPayment(orderDto)) {
            logger.info(">>>Заказ передан в дальнейшую обработку с ID: {}", orderDto.getOrderId());
            orderDto.setStatus(OrderStatus.PAID);
            kafkaTemplate.send("payed_orders", orderDto);

        } else {
            logger.info(">>>Заказ переведен  в стутус ожидания оплаты с ID: {}", orderDto.getOrderId());

        }

    }
}
