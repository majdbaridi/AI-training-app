package com.company.trainingapp.controller;

import com.company.trainingapp.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {
private KafkaProducer kafkaProducer;




public KafkaController(KafkaProducer kafkaProducer){

    this.kafkaProducer=kafkaProducer;
}

@GetMapping("/publish")
public ResponseEntity<String> publish(@RequestParam("message") String message){

    kafkaProducer.sendMessage(message);
    return ResponseEntity.ok("Message sent to the Topic");}

}
