package com.safelet.walletserver.repository;

import com.safelet.walletserver.model.Nonce;
import com.safelet.walletserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface NonceRepository extends JpaRepository<Nonce, User> {
	Optional<Nonce> findByUser(String user);
	Optional<Nonce> findByNonce(String token);
	Nonce save(Nonce token);
	@Transactional
	void removeByUser(String user);
}