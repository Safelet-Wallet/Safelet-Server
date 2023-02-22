package com.safelet.walletserver.service;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.model.Wallet;
import com.safelet.walletserver.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    public Optional<Wallet> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Double> getBalance(Long id){
        return  repository.findById(id).map(Wallet::getBalance);
    }

    public Wallet create(Wallet wallet){
        return repository.save(wallet);
    }

    public Wallet update(Wallet wallet) {
        return repository.save(wallet);
    }

    public boolean delete(Long id) {
		if (repository.existsById(id)){
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
