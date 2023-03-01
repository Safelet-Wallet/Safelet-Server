package com.safelet.walletserver.repository;

import com.safelet.walletserver.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceId(Long id);
    List<Transaction> findByDestinyId(Long id);
}
