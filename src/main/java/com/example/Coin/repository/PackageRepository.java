
package com.example.Coin.repository;

import com.example.Coin.models.Package;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PackageRepository extends MongoRepository<Package, String> {
}