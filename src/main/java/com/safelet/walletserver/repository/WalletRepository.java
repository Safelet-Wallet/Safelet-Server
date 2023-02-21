package com.safelet.walletserver.repository;

import com.safelet.walletserver.model.User;
import com.safelet.walletserver.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
