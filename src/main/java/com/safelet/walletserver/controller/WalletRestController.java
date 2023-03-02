package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.model.User;
import com.safelet.walletserver.service.UserService;
import com.safelet.walletserver.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
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

	@PostMapping("/address")
    public String getAddress(@RequestParam("token") String token){
        return walletService.getAddress(token);
    }

    @PostMapping("/eth/send")
    public String sendEthereum(@RequestParam("recipient") String toAddress, @RequestParam("amount") BigDecimal amount, @RequestParam("token") String token){
        return walletService.sendEthereum(toAddress, amount, token);
    }

	@GetMapping("/transactions/")
    public List<Transaction> getTransactions(@RequestParam("token") String token){
        return walletService.getTransactions(token);
    }

    @PostMapping("/address/new")
    public String createNewAddress(@RequestParam("token") String token){
        return walletService.createNewAddress(token);
    }

	@PostMapping("/register")
	public User register(@RequestParam("username") String username, @RequestParam("password") String password) {
		User user = new User();
		Base64.Decoder decoder = Base64.getDecoder();
		password = new String(decoder.decode(password.getBytes()));
		user.setUsername(username);
		user.setPassword(password);
		return userService.create(user);
	}

	//**NUEVO MD5 DIGEST HTTP**
	@PostMapping("/login/auth/request")
	public String loginWithoutAuth(@RequestParam("username") String username, @RequestParam("password") String password){
		return walletService.getRandomNonce(username);
	}

	//**NUEVO MD5 DIGEST HTTP**
	@PostMapping("/login/auth")
	public String loginDigest(@RequestParam("hash") String received_hash, @RequestParam("nonce") String nonce){
		User user =  walletService.digestLogin(received_hash, nonce);
		return (user != null) ? walletService.generateToken(user.getUsername()) : "";
	}


	//Ciframos en base64
	@PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
		Base64.Decoder decoder = Base64.getDecoder();
		password = new String(decoder.decode(password.getBytes()));
		System.out.println(password);
		Optional<User> user = userService.getByUsernameAndPassword(username, password);
		if(user.isPresent()){
			return walletService.generateToken(username);
		} else return "";
    }
}
