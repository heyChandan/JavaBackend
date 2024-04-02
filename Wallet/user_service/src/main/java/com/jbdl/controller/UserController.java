package com.jbdl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbdl.dto.UserResponseDto;
import com.jbdl.model.User;
import com.jbdl.service.UserOperationsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet-users")
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserOperationsService userOperationsService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody @Valid User user){
        try {
            return new ResponseEntity<>(userOperationsService.createUserAccount(user), HttpStatus.CREATED);
        }
        catch (Exception e){
            log.error(String.format("Runtime Exception occurred for user %d And Exception is %s", user.getId(), e.getMessage()));
        }

        return null;
    }
}
