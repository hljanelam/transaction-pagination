package com.synpulse8.transactionpagination.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic transactionTopic() {
        return TopicBuilder.name("transactionTopic")
//                .partitions(10)
                .build();
    }

    @Bean
    public NewTopic transactionJsonTopic() {
        return TopicBuilder.name("transaction_topic_json")
                .build();
    }
}
