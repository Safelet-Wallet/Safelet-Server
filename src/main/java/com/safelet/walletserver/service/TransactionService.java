package com.safelet.walletserver.service;

import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsSource(Long id){
        return transactionRepository.findBySourceId(id);
    }

    public List<Transaction> getTransactionsDestiny(Long id){
        return transactionRepository.findByDestinyId(id);
    }

    public Transaction create(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
