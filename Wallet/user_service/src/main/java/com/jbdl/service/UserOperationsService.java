package com.jbdl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.constants.TopicConstants;
import com.jbdl.dto.UserResponseDto;
import com.jbdl.dto.UserWalletCreationRequest;
import com.jbdl.model.User;
import com.jbdl.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class UserOperationsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public UserResponseDto createUserAccount(User user) throws JsonProcessingException {
        User savedUser = userRepository.save(user);
        //This dto Object will be messaged
        UserWalletCreationRequest walletCreationRequest = UserWalletCreationRequest.builder()
                .userId(savedUser.getId())
                .userName(savedUser.getUserFullName())
                .userEmailId(savedUser.getUserEmailId())
                .build();

        log.info( String.format("User Account is created for userId %d ,and userName %s", user.getId(), user.getUserFullName()));
        Future<SendResult<String, String>> send = kafkaTemplate.send(TopicConstants.USER_CREATION_TOPIC, savedUser.getId().toString(), objectMapper.writeValueAsString(walletCreationRequest));
        return UserResponseDto.builder()
                .message("Account is created, wallet creation is in progress. We will notify you over mail once it is done.")
                .user(savedUser)
                .build();
    }

    public Boolean checkUserById(Long userId) {
           return userRepository.existsById(userId);
    }

    public User fetchUserById(Long userId) {
        log.info("Fetch request received for user Id:{}",userId);
        return userRepository.findById(userId).get();
    }

    public String deleteUser(Long userId) {
        Boolean exist = userRepository.existsById(userId);
        if(exist){
        userRepository.deleteById(userId);
        return String.format("User with id : %d is deleted", userId);
        }
        return String.format("User with id : %d is not exist", userId);
    }
}
