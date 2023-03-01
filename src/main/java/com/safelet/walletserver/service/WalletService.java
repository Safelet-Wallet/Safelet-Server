package com.safelet.walletserver.service;

import com.safelet.walletserver.crpyto.ethereum.WalletManager;
import com.safelet.walletserver.model.AuthToken;
import com.safelet.walletserver.model.User;
import com.safelet.walletserver.repository.TokenRepository;
import com.safelet.walletserver.repository.UserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WalletService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

	private WalletManager walletManager = new WalletManager();

    public WalletService(UserRepository userRepository, TokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
    }

    public BigInteger getBalanceByAddress(String address){
		try {
			return walletManager.getBalance(address);
		} catch (IOException e) {
			System.out.println("Error getting balances for: " + address);
		}
		return null;
	}

	public String createNewAddress(String token){

		Optional<AuthToken> cred = tokenRepository.findByToken(token);
		if (cred.isPresent()){
			User user = userRepository.findByUsername(cred.get().getUser()).get();
			System.out.println(user.getUsername());
			try {
				Credentials credentials = walletManager.createWallet();
				user.setPrivateKey(String.valueOf(credentials.getEcKeyPair().getPrivateKey()));
				user.setPublicKey(String.valueOf(credentials.getEcKeyPair().getPublicKey()));
				user.setAddress(String.valueOf(credentials.getAddress()));
				userRepository.save(user);

				return user.getAddress();

			} catch (IOException | CipherException | InvalidAlgorithmParameterException| NoSuchAlgorithmException| NoSuchProviderException e) {
				System.out.println("Error creating address.");
				System.out.println(e.toString());
				System.out.println(e.getMessage());
			}
		}

		return "";
	}

	public String generateToken(String username){
		tokenRepository.removeByUser(username);
		String token = new String(DatatypeConverter.parseBase64Binary(LocalDateTime.now().toString()));
		tokenRepository.save(new AuthToken(username,token));
		return token;
	}
}
