package com.safelet.walletserver.service;

import com.safelet.walletserver.crpyto.ethereum.WalletManager;
import com.safelet.walletserver.model.AuthToken;
import com.safelet.walletserver.model.Nonce;
import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.model.User;
import com.safelet.walletserver.repository.NonceRepository;
import com.safelet.walletserver.repository.TokenRepository;
import com.safelet.walletserver.repository.TransactionRepository;
import com.safelet.walletserver.repository.UserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class WalletService {

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final TransactionRepository transactionRepository;
	private final NonceRepository nonceRepository;

	private final WalletManager walletManager = new WalletManager();

	public WalletService(UserRepository userRepository, TokenRepository tokenRepository, TransactionRepository transactionRepository, NonceRepository nonceRepository) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.transactionRepository = transactionRepository;
		this.nonceRepository = nonceRepository;
	}

	public BigInteger getBalanceByAddress(String address) {

		try {

			return walletManager.getBalance(address);

		} catch (IOException e) {
			System.out.println("Error getting balances for: " + address);
		}

		return null;
	}

	public String createNewAddress(String token) {

		Optional<AuthToken> cred = tokenRepository.findByToken(token);
		if (cred.isPresent()) {
			User user = userRepository.findByUsername(cred.get().getUser()).get();
			System.out.println(user.getUsername());
			try {
				String url = walletManager.createWallet();

				Credentials credentials = walletManager.getCredentialsFrom(url);
				user.setWalletUrl(url);
				userRepository.save(user);

				return credentials.getAddress();

			} catch (IOException | CipherException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
				System.out.println("Error creating address.");
				System.out.println(e);
				System.out.println(e.getMessage());
			}
		} else {
			return "Invalid AuthToken";
		}

		return "";
	}

	public String generateToken(String username) {
		tokenRepository.removeByUser(username);
		String token = new String(DatatypeConverter.parseBase64Binary(LocalDateTime.now() + username));
		tokenRepository.save(new AuthToken(username, token));

		new Thread(() -> {
			try {

				TimeUnit.MINUTES.sleep(15);
				tokenRepository.removeByUser(username);

			} catch (InterruptedException e) {
				System.out.println("token delete stopped.");
			}
		}).start();

		return token;
	}

	public String sendEthereum(String toAddress, BigDecimal amount, String token) {

		Optional<AuthToken> cred = tokenRepository.findByToken(token);

		if (cred.isPresent()) {

			User user = userRepository.findByUsername(cred.get().getUser()).get();

			Transaction transaction = new Transaction();
			transaction.setSource(user);
			transaction.setAmount(amount);
			transaction.setToAddress(toAddress);

			Transaction finalTransaction = transactionRepository.save(transaction);

			Thread thread = new Thread(() -> {

				try {
					Credentials credentials = WalletUtils.loadCredentials("safelet", user.getWalletUrl());
					walletManager.sendEther(credentials, toAddress, amount);
					finalTransaction.setStatus("OK");
					transactionRepository.save(finalTransaction);

				} catch (Exception e) {
					System.out.println(e);
					finalTransaction.setStatus("CANCELLED");
					transactionRepository.save(finalTransaction);
				}
			});

			thread.start();

			return "OK";

		} else {
			return "Invalid AuthToken";
		}
	}

	public List<Transaction> getTransactions(String token) {
		Optional<AuthToken> cred = tokenRepository.findByToken(token);
		if (cred.isPresent()) {
			User user = userRepository.findByUsername(cred.get().getUser()).get();
			return transactionRepository.findBySource(user);
		}
		return null;
	}

	public String getRandomNonce(String user) {
		byte[] resBuf = new byte[50];
		new Random().nextBytes(resBuf);
		String nonce = new String(Hex.encode(resBuf));
		nonceRepository.save(new Nonce(user, nonce));
		return nonce;
	}

	public User digestLogin(String received_hash, String nonce) {
		Optional<Nonce> nonObj = nonceRepository.findByNonce(nonce);
		if (nonObj.isEmpty()) return null;

		Optional<User> userOptional = userRepository.findByUsername(nonObj.get().getUser());
		if (userOptional.isPresent()) {

			User user = userOptional.get();
			String hashedLocal = DigestUtils.md5DigestAsHex((user.getUsername() + ":" + user.getPassword() + ":" + nonce).getBytes());

			return (received_hash.equals(hashedLocal)) ? user : null;
		}

		return null;
	}

	public String getAddress(String token) {

		Optional<AuthToken> cred = tokenRepository.findByToken(token);
		if (cred.isPresent()) {
			User user = userRepository.findByUsername(cred.get().getUser()).get();

			try {
				String url = user.getWalletUrl();
				Credentials credentials = walletManager.getCredentialsFrom(url);
				return credentials.getAddress();

			} catch (IOException | CipherException  e) {
				System.out.println("Error finding address.");
				System.out.println(e);
				System.out.println(e.getMessage());
			}
		} else {
			return "Invalid AuthToken";
		}
		return null;
	}
}
