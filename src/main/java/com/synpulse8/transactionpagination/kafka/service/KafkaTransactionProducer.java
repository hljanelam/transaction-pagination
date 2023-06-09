package com.synpulse8.transactionpagination.kafka.service;

import com.synpulse8.transactionpagination.payload.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaTransactionProducer {

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    public void sendMessage(Transaction transaction) {

        Message<Transaction> transactionMessage = MessageBuilder
                .withPayload(transaction)
                .setHeader(KafkaHeaders.TOPIC, "transaction_topic_json")
                .build();
        log.info("$$$$ => Producing message: {}", transactionMessage);

        kafkaTemplate.send(transactionMessage);
    }

}
