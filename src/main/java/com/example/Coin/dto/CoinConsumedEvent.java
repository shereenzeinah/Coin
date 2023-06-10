package com.example.Coin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoinConsumedEvent {

    public long userId;
    public String date;
    public long amount;
}
