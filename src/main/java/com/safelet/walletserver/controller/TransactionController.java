package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public Transaction create(@RequestBody Transaction transaction){
        return transactionService.create(transaction);
    }

    @GetMapping("/received-transactions/{id}")
    public List<Transaction> getTransactionsSource(@PathVariable(name = "id") Long id){
        return transactionService.getTransactionsSource(id);
    }

    @GetMapping("/sent-transactions/{id}")
    public List<Transaction> getTransactionsDestiny(@PathVariable(name = "id") Long id){
        return transactionService.getTransactionsDestiny(id);
    }
}
