package com.example.Coin.repository;

import com.example.Coin.models.Coin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends MongoRepository<Coin, String> {

    Coin findByUserId(Long userId);
}
