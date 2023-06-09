package com.example.Coin.services.coin;

import com.example.Coin.models.Coin;
import com.example.Coin.models.Transactions;
import com.example.Coin.repository.CoinRepository;
import com.example.Coin.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CoinServiceImpl implements CoinService {

    @Value("${spring.kafka.topic.coins.added}")
    public String COINS_ADDED_TOPIC;

    @Value("${spring.kafka.topic.coins.used}")
    public String COINS_USED_TOPIC;

    @Value("${spring.kafka.topic.coins.purchased}")
    public String COINS_PURCHASED_TOPIC;

    @Autowired
    CoinRepository coinRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public int getCoins(Long userId) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        if(userCoinObject == null) return 0;
        return userCoinObject.getBalanceAvailable();
    }

    @Override
    public Boolean addCoins(Long userId, int coins) {
        // Add coins
        Coin userCoinObject = coinRepository.findByUserId(userId);
        if (userCoinObject == null) {
            userCoinObject = new Coin();
            userCoinObject.setBalanceAvailable(0);
            userCoinObject.setUserId(userId);
        }
        int newBalance = userCoinObject.getBalanceAvailable() + coins;
        userCoinObject.setBalanceAvailable(newBalance);
        // Update transactions
        Transactions transactions = Transactions.builder()
                .transactionType("ADD-COINS")
                .transactionDate(new Date())
                .description("New coins added to user")
                .amount(coins)
                .userId(userId)
                .build();
        transactionsRepository.save(transactions);
        coinRepository.save(userCoinObject);
        // publish "coins-added" to the kafka stream
        kafkaTemplate.send(COINS_ADDED_TOPIC, userCoinObject);

        return true;
    }

    @Override
    public Boolean deductCoins(Long userId, int deductedAmount) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        int newBalance = userCoinObject.getBalanceAvailable() - deductedAmount;
        userCoinObject.setBalanceAvailable(newBalance);
        // Update transactions
        Transactions transactions = Transactions.builder()
                .transactionType("DEDUCT-COINS")
                .transactionDate(new Date())
                .description("Coins has been deducted from the user")
                .amount(deductedAmount)
                .userId(userId)
                .build();
        transactionsRepository.save(transactions);
        coinRepository.save(userCoinObject);

        // publish "coins-used" to the kafka stream
        kafkaTemplate.send(COINS_USED_TOPIC, userCoinObject);

        return null;
    }

    @Override
    public Boolean validateBalance(Long userId, int amount) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        if (userCoinObject.getBalanceAvailable() >= amount) {
            return true;
        }
        return false;
    }
}
