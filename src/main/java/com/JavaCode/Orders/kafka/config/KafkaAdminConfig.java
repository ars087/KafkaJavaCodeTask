package com.JavaCode.Orders.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaAdminConfig {

    @Bean
    public NewTopic newOrdersTopic() {
        return new NewTopic("new_orders", 3, (short) 1);
    }

    @Bean
    public NewTopic payedOrdersTopic() {
        return new NewTopic("payed_orders", 3, (short) 1);
    }

    @Bean
    public NewTopic sentOrdersTopic() {
        return new NewTopic("sent_orders", 3, (short) 1);
    }
}