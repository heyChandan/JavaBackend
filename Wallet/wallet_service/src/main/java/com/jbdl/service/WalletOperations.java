package com.jbdl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.constants.TopicConstants;
import com.jbdl.dto.SendMailNotification;
import com.jbdl.dto.UserWalletCreationRequest;
import com.jbdl.enums.ServiceType;
import com.jbdl.model.Wallet;
import com.jbdl.repository.WalletRepository;
import com.jbdl.templates.MailsTemplates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Slf4j
public class WalletOperations {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void createWalletForNewUser(UserWalletCreationRequest receivedData) throws JsonProcessingException {
        Long userId = receivedData.getUserId();
        Wallet newWallet = new Wallet(userId);
        log.info(String.format("New Wallet Details for user Id: %d are wallet: %s", userId, newWallet.toString()));
        Wallet newCreatedWallet = walletRepository.save(newWallet);
        log.info(String.format("Saved Wallet Details for user Id: %d are wallet: %s", userId, newCreatedWallet.toString()));

        SendMailNotification sendMailNotification = SendMailNotification.builder()
                .receiverMailId(receivedData.getUserEmailId())
                .message(String.format(MailsTemplates.getWalletCreationMailBody().getMailBody(), receivedData.getUserName()))
                .serviceType(ServiceType.WALLET_SERVICE)
                .Subject(MailsTemplates.getWalletCreationMailBody().getMailSubject())
                .build();
        Future<SendResult<String, String>> send = kafkaTemplate.send(TopicConstants.SEND_NOTIFICATION_TOPIC, newCreatedWallet.getUserId().toString(), objectMapper.writeValueAsString(sendMailNotification));
    }
}
