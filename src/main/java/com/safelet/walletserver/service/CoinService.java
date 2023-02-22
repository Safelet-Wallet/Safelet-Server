package com.safelet.walletserver.service;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.repository.CoinRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoinService {

    private final CoinRepository repository;

    public CoinService(CoinRepository repository) {
        this.repository = repository;
    }

    public Optional<Coin> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Coin> getByCode(String code) {
        return repository.findByCode(code);
    }

    public Coin create(Coin coin){
        return repository.save(coin);
    }

    public Coin update(Coin coin) {
        return repository.save(coin);
    }

    public boolean delete(Long id) {
		if (repository.existsById(id)){
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
