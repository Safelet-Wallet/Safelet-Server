package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.model.Wallet;
import com.safelet.walletserver.service.CoinService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("coin")
public class CoinController {

    private final CoinService service;

    public CoinController(CoinService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Optional<Coin> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("code/{code}")
    public Optional<Coin> getByCode(@PathVariable("code") String code) {
        return service.getByCode(code);
    }

    @PostMapping
	public Coin create(@RequestBody Coin coin) {
		return service.create(coin);
	}

	@PutMapping
	public Coin update(@RequestBody Coin coin) {
		return service.update(coin);
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
}
