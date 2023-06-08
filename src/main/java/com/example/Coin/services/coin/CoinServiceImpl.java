package com.example.Coin.services.coin;

import com.example.Coin.models.Coin;
import com.example.Coin.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    CoinRepository coinRepository;

    @Override
    public Long getCoins(Long userId) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        return userCoinObject.getBalanceAvailable();
    }

    @Override
    public Boolean addCoins(Long userId, Long coins) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        long newBalance = userCoinObject.getBalanceAvailable() + coins;
        userCoinObject.setBalanceAvailable(newBalance);
        return null;
    }

    @Override
    public Boolean deductCoins(Long userId, Long deductedAmount) {
        Coin userCoinObject = coinRepository.findByUserId(userId);
        long newBalance = userCoinObject.getBalanceAvailable()  - deductedAmount;
        userCoinObject.setBalanceAvailable(newBalance);
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
