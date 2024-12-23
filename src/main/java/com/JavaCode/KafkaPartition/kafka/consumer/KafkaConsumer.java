package com.JavaCode.KafkaPartition.kafka.consumer;


import com.JavaCode.KafkaPartition.dto.WebLogDto;
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

    public final KafkaTemplate<String, WebLogDto> kafkaTemplate;

    public KafkaConsumer(KafkaTemplate<String, WebLogDto> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    @RetryableTopic(
        attempts = "2",
        backoff = @Backoff(delay = 1500, multiplier = 2),
        dltTopicSuffix = ".dlt"
    )
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "web-log-group")
    public void listenOrder(WebLogDto webLogDto) {

        logger.info(" Логи приняты {}", webLogDto);

    }
}
