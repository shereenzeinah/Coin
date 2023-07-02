package com.example.Coin.repository;

import com.example.Coin.models.Transactions;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends MongoRepository<Transactions, Integer> {
    List<Transactions> findByUserId(Long userId);
}
