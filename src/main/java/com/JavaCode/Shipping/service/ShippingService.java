package com.JavaCode.Shipping.service;

import com.JavaCode.Shipping.dto.OrderDto;
import com.JavaCode.Shipping.dto.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
    private static final Logger logger = LoggerFactory.getLogger(ShippingService.class);

    public OrderDto productPackaging(OrderDto orderDto) {
        logger.info(">>>Заказ поступил на упаковку с ID: {}", orderDto.getOrderId());
        if (orderDto.getStatus() == OrderStatus.PAID) {
            logger.info(">>>Заказ упакован с ID: {}", orderDto.getOrderId());
            orderDto.setStatus(OrderStatus.SHIPPED);
        }
        return orderDto;
    }
}
