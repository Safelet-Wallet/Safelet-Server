package com.safelet.walletserver.service;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.repository.CoinRepository;
import org.springframework.stereotype.Service;

@Service
public class CoinService {

    private final CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public Coin create(Coin coin){
        return coinRepository.save(coin);
    }
}
