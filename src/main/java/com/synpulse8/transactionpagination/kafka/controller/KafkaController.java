package com.synpulse8.transactionpagination.kafka.controller;

import com.synpulse8.transactionpagination.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    ProducerService producerService;

    @GetMapping(value = "/publish")
    public ResponseEntity<String> sendMessageToKafkaTopic(@RequestParam String message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to the Kafka Topic java_in_use_topic Successfully");
    }

}
