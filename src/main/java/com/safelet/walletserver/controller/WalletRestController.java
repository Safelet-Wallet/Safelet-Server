package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Wallet;
import com.safelet.walletserver.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("wallet")
public class WalletRestController {

    private WalletService service;

    public WalletRestController(WalletService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Optional<Wallet> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/balance/{id}")
    public Optional<Double> getBalance(@PathVariable("id") Long id){
        return service.getBalance(id);
    }

    @PostMapping
	public Wallet create(@RequestBody Wallet wallet) {
		return service.create(wallet);
	}

	@PutMapping
	public Wallet update(@RequestBody Wallet wallet) {
		return service.update(wallet);
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
}
