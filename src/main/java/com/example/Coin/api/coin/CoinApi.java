package com.example.Coin.api.coin;

import com.example.Coin.services.coin.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coins")
public class CoinApi {

    @Autowired
    private CoinService coinService;

    @GetMapping(path = "/{userId}", produces = "application/json")
    public int getBalance(@PathVariable Long userId) {
        return coinService.getCoins(userId);
    }

}
