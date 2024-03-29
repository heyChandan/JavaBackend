package com.apacheKafkaProducer.apacheKafkaDemoProducer.controller;

import com.apacheKafkaProducer.apacheKafkaDemoProducer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(String message){
        long round = Math.round(Math.random()*10000);
        kafkaService.sendMessage("My roll no is : " + round);
        return new ResponseEntity<>("Message sent successfully with the roll no-"+round, HttpStatus.OK);
    }
}
