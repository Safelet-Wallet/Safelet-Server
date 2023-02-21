package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
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
