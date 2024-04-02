package com.jbdl.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.constants.TopicConstants;
import com.jbdl.service.WalletOperations;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

@Configuration
@Slf4j
public class KafkaConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WalletOperations walletOperations;

    @KafkaListener(topics = TopicConstants.USER_CREATION_TOPIC, groupId = "user_service.walletCreation")
    public void pollMessagesForUserCreation(ConsumerRecord receivedMessage) throws JsonProcessingException {
        Map<String,Long> receivedData = objectMapper.readValue(receivedMessage.value().toString(), Map.class);
        log.info("ReceivedData: {}", receivedData.get("userId"));
        walletOperations.createWalletForNewUser(receivedData.get("userId"));
    }
}
