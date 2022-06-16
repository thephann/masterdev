package com.example.kafka_spring_demo.controller;

import com.example.kafka_spring_demo.kafka.CSVProducer;
import com.example.kafka_spring_demo.kafka.ReadCSV;
import com.example.kafka_spring_demo.schema.customer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kafka")
public class CSVMessageController {
    private CSVProducer kafkaProducer;

    public CSVMessageController(CSVProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    ReadCSV readCSV = new ReadCSV();
    List customerList = readCSV.ReadCSVFile();

    for (Object customerObject:customerList) {
        customer cusObj = (customer) customerObject;
        kafkaProducer.sendMessage(new ProducerRecord<String,customer>("customer",cusObj.getTelephone(),cusObj));

    }

    @GetMapping("publish")
    public ResponseEntity<String> publish(customer cus) {
        kafkaProducer.sendMessage(cus);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
