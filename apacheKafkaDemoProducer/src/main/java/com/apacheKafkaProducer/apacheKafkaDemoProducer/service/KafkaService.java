package com.apacheKafkaProducer.apacheKafkaDemoProducer.service;

import com.apacheKafkaProducer.apacheKafkaDemoProducer.constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean sendMessage(String message){
        kafkaTemplate.send(constants.TOPIC_NAME, message);
        return  true;
    }
}
