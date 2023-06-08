package com.example.Coin.services.coin;

public interface CoinService {

    Long getCoins(Long userId);

    Boolean addCoins(Long userId, Long coins);

    Boolean deductCoins(Long userId, Long deductedAmount);

    Boolean validateBalance(Long userId, Long amount);


}
