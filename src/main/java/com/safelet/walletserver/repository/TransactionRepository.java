package com.safelet.walletserver.repository;

import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySource(User source);
    Optional<Transaction> findBySourceId(Long id);
    Transaction save(Transaction transaction);
    @Transactional
    void removeById(Long id);
}
