package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Wallet;
import com.safelet.walletserver.service.WalletService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wallet")
public class WalletController {

    private WalletService walletService;

    @PostMapping("/add")
    public Wallet create(@RequestBody Wallet wallet){
        return walletService.create(wallet);
    }

    @GetMapping("/balance")
    public Double getBalance(@PathVariable Long id){
        return walletService.getBalance(id);
    }
}
