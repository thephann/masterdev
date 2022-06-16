package com.example.kafka_spring_demo.controller;

import com.example.kafka_spring_demo.kafka.CSVProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")

public class MessageController {
    private final CSVProducer kafkaProducer;
    // http:localhost:8080/api/v1/kafka/publish?message=hello world

    public MessageController(CSVProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

//    @GetMapping("/publish")
//    public ResponseEntity<String> publish(@RequestParam("message") String message) {
//
//        kafkaProducer.sendMessage(message);
//        return ResponseEntity.ok("Mess sent to topic");
//    }
}
