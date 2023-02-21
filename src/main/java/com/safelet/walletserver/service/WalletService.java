package com.safelet.walletserver.service;

import com.safelet.walletserver.model.Wallet;
import com.safelet.walletserver.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet create(Wallet wallet){
        return walletRepository.save(wallet);
    }

    public Double getBalance(Long id){
        return walletRepository.findById(id).get().getBalance();
    }
}
