package com.safelet.walletserver.repository;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {
	Optional<Coin> findByCode(String code);

}
