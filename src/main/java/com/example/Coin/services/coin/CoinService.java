package com.example.Coin.services.coin;

public interface CoinService {

    int getCoins(Long userId);

    Boolean addCoins(Long userId, int coins);

    Boolean deductCoins(Long userId, int deductedAmount);

    Boolean validateBalance(Long userId, int amount);

}
