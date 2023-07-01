package com.example.Coin.services.coin;

import com.example.Coin.models.Coin;
import com.example.Coin.models.Transactions;
import com.example.Coin.repository.CoinRepository;
import com.example.Coin.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    CoinRepository coinRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Override
    public Long getCoins(Long userId) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        return userCoinObject.getBalanceAvailable();
    }

    @Override
    public Boolean addCoins(Long userId, Long coins) {
        //Add coins
        Coin userCoinObject = coinRepository.findByUserId(userId);
        long newBalance = userCoinObject.getBalanceAvailable() + coins;
        userCoinObject.setBalanceAvailable(newBalance);
        //Update transactions
        Transactions transactions = Transactions.builder()
                .transactionType("ADD-COINS")
                .transactionDate(new Date())
                .description("New coins added to user")
                .amount(coins)
                .userId(userId)
                .build();
        transactionsRepository.save(transactions);
        coinRepository.save(userCoinObject);
        return true;
    }

    @Override
    public Boolean deductCoins(Long userId, Long deductedAmount) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        long newBalance = userCoinObject.getBalanceAvailable()  - deductedAmount;
        userCoinObject.setBalanceAvailable(newBalance);
        //Update transactions
        Transactions transactions = Transactions.builder()
                .transactionType("DEDUCT-COINS")
                .transactionDate(new Date())
                .description("Coins has been deducted from the user")
                .amount(deductedAmount)
                .userId(userId)
                .build();
        transactionsRepository.save(transactions);
        coinRepository.save(userCoinObject);
        return null;
    }

    @Override
    public Boolean validateBalance(Long userId, Long amount) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        if(userCoinObject.getBalanceAvailable() >= amount) {
            return true;
        }
        return false;
    }
}
