package com.safelet.walletserver.repository;

import com.safelet.walletserver.model.AuthToken;
import com.safelet.walletserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<AuthToken, User> {
	Optional<AuthToken> findByUser(String user);
	Optional<AuthToken> findByToken(String token);
	AuthToken save(AuthToken token);
	@Transactional
	void removeByUser(String user);
}
