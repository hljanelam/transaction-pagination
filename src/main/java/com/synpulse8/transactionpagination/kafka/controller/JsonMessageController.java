package com.synpulse8.transactionpagination.kafka.controller;

import com.synpulse8.transactionpagination.kafka.service.KafkaTransactionProducer;
import com.synpulse8.transactionpagination.payload.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class JsonMessageController {

    @Autowired
    private KafkaTransactionProducer kafkaTransactionProducer;

    @PostMapping("/publishTransaction")
    public ResponseEntity<String> publish (@RequestBody Transaction transaction) {
        kafkaTransactionProducer.sendMessage(transaction);
        return ResponseEntity.ok("Transaction sent to topic");
    }
}
