package com.example.kafka_spring_demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service

public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTemplate.class);
    private KafkaTemplate<String,String> kafkaTemplate;

//    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
    public void sendMessage(String mess) {
        kafkaTemplate.send("customer",mess);
    }
}
