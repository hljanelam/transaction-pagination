package com.synpulse8.transactionpagination.kafka.service;

import com.synpulse8.transactionpagination.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(topics = "transactionTopic", groupId = "synpulse_transaction_group_id")
    public void consume(String message) {
        log.info("$$$$ => Consumed message: {}", message);
    }

    @KafkaListener(topics = "transaction_topic_json", groupId = "synpulse_transaction_group_id")
    public void consume(Transaction transaction) {
        log.info("Transaction message received => {}", transaction);

    }

}
