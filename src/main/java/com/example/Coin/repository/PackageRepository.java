
package com.example.Coin.repository;

import com.example.Coin.models.Package;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PackageRepository extends MongoRepository<Package, String> {
}