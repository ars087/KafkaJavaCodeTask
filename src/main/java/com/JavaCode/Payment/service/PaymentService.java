package com.JavaCode.Payment.service;

import com.JavaCode.Payment.dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final Random random = new Random();

    public boolean processPayment(OrderDto orderDto) {
        double successProbability = 0.8;
        boolean isPaymentSuccessful = random.nextDouble() < successProbability;
        if (isPaymentSuccessful) {
            logger.info(">>>Платеж успешно выполнен для заказа с ID: {}", orderDto.getOrderId());
            return true;
        } else {
            logger.warn(">>>Платеж не выполнен для заказа с ID: {}", orderDto.getOrderId());
            return false;
        }

    }

}



