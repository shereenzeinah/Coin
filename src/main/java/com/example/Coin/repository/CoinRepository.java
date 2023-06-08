package com.example.Coin.repository;

import com.example.Coin.models.Coin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends MongoRepository<Coin, Long> {

    Coin findByUserId(Long userId);
}
