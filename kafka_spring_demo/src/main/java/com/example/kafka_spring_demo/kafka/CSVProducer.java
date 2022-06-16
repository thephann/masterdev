package com.example.kafka_spring_demo.kafka;

import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Data
@Service
@EnableAutoConfiguration
public class CSVProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTemplate.class);

    KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>();




}