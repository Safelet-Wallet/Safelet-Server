package com.safelet.walletserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Esta clase tiene habilitada la seguridad básica.
 *
 * @author Andres Sanchez
 */

@SpringBootApplication
public class WalletserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletserverApplication.class, args);
	}

}
