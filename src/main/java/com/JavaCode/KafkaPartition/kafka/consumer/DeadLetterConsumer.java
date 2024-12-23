package com.JavaCode.KafkaPartition.kafka.consumer;

import com.JavaCode.KafkaPartition.dto.WebLogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeadLetterConsumer {
    private static final Logger logger = LoggerFactory.getLogger(DeadLetterConsumer.class);

    @KafkaListener(topics = "web_log.dlt", groupId = "payment-group")
    public void listenDeadLetter(WebLogDto webLogDto) {
        logger.info(">>> Сохранение лога  в БД ID: {}", webLogDto.getId());
    }
}