package com.safelet.walletserver.crpyto.ethereum;

import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class WalletManager {

	private final Web3j web3j;
	private final String folder = System.getProperty("user.dir") + "/wallets/";
	String infuraEndpoint = "https://sepolia.infura.io/v3/fbef33a572114ed99778a65416398b05";

	public WalletManager() {
		this.web3j = Web3j.build(new HttpService(infuraEndpoint));
	}

	public String createWallet() throws IOException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
		File walletDirectory = new File(folder);
		return WalletUtils.generateFullNewWalletFile("safelet",walletDirectory);
	}

	public Credentials getCredentialsFrom(String url) throws CipherException, IOException {
		return WalletUtils.loadCredentials("safelet", System.getProperty("user.dir") + "/wallets/" + url);
	}

	public BigInteger getBalance(String address) throws IOException {
		EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
		return ethGetBalance.getBalance();
	}

	public void sendEther(Credentials credentials, String toAddress, BigDecimal amount) throws Exception {
		BigInteger value = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();
		TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, toAddress, amount, Convert.Unit.ETHER).send();

	}
}