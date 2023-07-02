package com.example.Coin.api.transactions;

import com.example.Coin.models.Transactions;
import com.example.Coin.services.transactions.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionApi {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(path = "/{userId}", produces = "application/json")
    public List<Transactions> getTransactions(@PathVariable Long userId) {
        return transactionService.getTransactions(userId);
    }
}
