package com.example.kafka_spring_demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic customersTopic() {
        return TopicBuilder.name("customer")
                .partitions(5)
                .replicas(1)
                .build();
    }

}
