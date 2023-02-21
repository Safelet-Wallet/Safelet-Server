package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.service.CoinService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("coin")
public class CoinController {

    private CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @PostMapping("/add")
    public Coin create(@RequestBody Coin coin){
        return coinService.create(coin);
    }
}
