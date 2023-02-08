package com.safelet.walletserver.repository;

import com.safelet.walletserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<User, Long> {
}