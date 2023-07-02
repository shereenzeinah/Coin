package com.example.Coin.services.transactions;

import java.util.List;

import com.example.Coin.models.Transactions;

public interface TransactionService {
    List<Transactions> getTransactions(Long userId);
}
