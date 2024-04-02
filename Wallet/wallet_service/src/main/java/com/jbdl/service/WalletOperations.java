package com.jbdl.service;

import com.jbdl.model.Wallet;
import com.jbdl.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletOperations {

    @Autowired
    WalletRepository walletRepository;
    public void createWalletForNewUser(Object userId) {
        Wallet newWallet = new Wallet(((Number) userId).longValue());
        log.info(String.format("New Wallet Details for user Id: %d are wallet: %s", userId, newWallet.toString()));
        Wallet newCreatedWallet = walletRepository.save(newWallet);
        log.info(String.format("New Wallet Details for user Id: %d are wallet: %s", userId, newCreatedWallet.toString()));
    }
}
