package com.apacheKafkaConsumer.apacheKafkaDemoConsumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    @KafkaListener(topics = "jbdl63", groupId ="jbdlPractice-1")
    public void messageListener(String message){
        System.out.println(message);
    }
}
