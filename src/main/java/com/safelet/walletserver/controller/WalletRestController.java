package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.User;
import com.safelet.walletserver.service.UserService;
import com.safelet.walletserver.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("")
public class WalletRestController {

    private final WalletService walletService;
	private final UserService userService;

	public WalletRestController(WalletService walletService, UserService userService) {
		this.walletService = walletService;
		this.userService = userService;
	}

    @GetMapping("/{address}")
    public BigInteger getBalance(@PathVariable("address") String address){
        return walletService.getBalanceByAddress(address);
    }

    @PostMapping("/address/new")
    public String createNewAddress(@RequestParam("token") String token){
        return walletService.createNewAddress(token);
    }

	//Ciframos en base64
	@PostMapping("/login/")
    public String createUser(@RequestParam("username") String username, @RequestParam("password") String password){

		Base64.Decoder decoder = Base64.getDecoder();
		password =new String(decoder.decode(password.getBytes()));
		System.out.println(password);
		Optional<User> user = userService.findByUsernameAndPassword(username, password);
		if(user.isPresent()){
			return walletService.generateToken(username);
		} else return "";
    }
}
