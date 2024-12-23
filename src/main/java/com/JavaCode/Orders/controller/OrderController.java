package com.JavaCode.Orders.controller;

import com.JavaCode.Orders.dto.OrderDto;
import com.JavaCode.Orders.service.OrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {

        logger.info(">>>Входящий запрос на контроллер с ID: {}", orderDto.getOrderId());
        if (orderProducer.sendOrder(orderDto)) {

            return ResponseEntity.status(HttpStatus.CREATED).body("Заказ создан");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Заказ не отправлен. Сбой при отправке");
    }
}