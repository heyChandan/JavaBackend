package com.apacheKafkaProducer.apacheKafkaDemoProducer.config;

import com.apacheKafkaProducer.apacheKafkaDemoProducer.constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ApacheKafkaConfig {
    public NewTopic topic(){
        return TopicBuilder.name(constants.TOPIC_NAME).build();
    }
}
