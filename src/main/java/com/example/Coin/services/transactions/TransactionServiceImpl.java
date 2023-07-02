package com.example.Coin.services.transactions;

import com.example.Coin.models.Transactions;
import com.example.Coin.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionsRepository transactionsRepository;

    @Override
    public List<Transactions> getTransactions(Long userId) {
        return transactionsRepository.findByUserId(userId);
    }

    @Autowired
    public void setTransactionsRepository(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }
}
