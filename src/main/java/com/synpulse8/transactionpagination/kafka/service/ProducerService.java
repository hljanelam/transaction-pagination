package com.synpulse8.transactionpagination.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

    String kafkaTopic = "transactionTopic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("$$$$ => Producing message: {}", message);

        kafkaTemplate.send(kafkaTopic, message);

    }
}
