package com.safelet.walletserver.controller;

import com.safelet.walletserver.model.Transaction;
import com.safelet.walletserver.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transaction")
public class TransactionRestController {

    private final TransactionService service;

    public TransactionRestController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Optional<Transaction> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/received-transactions/{id}")
    public List<Transaction> getTransactionsSource(@PathVariable(name = "id") Long id){
        return service.getTransactionsSource(id);
    }

    @GetMapping("/sent-transactions/{id}")
    public List<Transaction> getTransactionsDestiny(@PathVariable(name = "id") Long id){
        return service.getTransactionsDestiny(id);
    }

    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return service.create(transaction);
    }

    @PutMapping
    public Transaction update(@RequestBody Transaction transaction) {
        return service.update(transaction);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
