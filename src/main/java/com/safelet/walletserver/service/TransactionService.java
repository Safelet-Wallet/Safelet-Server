package com.safelet.walletserver.service;

import com.safelet.walletserver.model.Coin;
import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.model.Wallet;
import com.safelet.walletserver.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Optional<Transaction> getById(Long id) {
        return repository.findById(id);
    }

    public List<Transaction> getTransactionsSource(Long id){
        return repository.findBySourceId(id);
    }

    public List<Transaction> getTransactionsDestiny(Long id){
        return repository.findByDestinyId(id);
    }

    public Transaction create(Transaction transaction){
        return repository.save(transaction);
    }

    public Transaction update(Transaction transaction) {
        return repository.save(transaction);
    }

    public boolean delete(Long id) {
		if (repository.existsById(id)){
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
