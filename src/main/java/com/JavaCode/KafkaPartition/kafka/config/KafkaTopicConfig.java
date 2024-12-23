package com.JavaCode.KafkaPartition.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicConfig {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Value("${kafka.topic.partitions}")
    private int numPartitions;

    @Value("${kafka.topic.replication-factor}")
    private short replicationFactor;

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, numPartitions, replicationFactor);
    }
}
