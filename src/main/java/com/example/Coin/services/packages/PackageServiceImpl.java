package com.example.Coin.services.packages;

import com.example.Coin.models.Package;
import com.example.Coin.models.PackageDTO;
import com.example.Coin.models.PackageDeletedEvent;
import com.example.Coin.models.Transactions;
import com.example.Coin.repository.PackageRepository;
import com.example.Coin.repository.TransactionsRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    @Value("${spring.kafka.topic.package.added}")
    public String PACKAGE_ADDED_TOPIC;

    @Value("${spring.kafka.topic.package.updated}")
    public String PACKAGE_UPDATED_TOPIC;

    @Value("${spring.kafka.topic.package.deleted}")
    public String PACKAGE_DELETED_TOPIC;

    @Autowired
    PackageRepository repository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public List<Package> getAll() {
        return repository.findAll();
    }

    @Override
    public Package createPackage(PackageDTO packageDTO) {
        Package packageObj = Package.builder().name(packageDTO.name)
                .description(packageDTO.description).coins(packageDTO.coins)
                .build();

        repository.save(packageObj);
        Transactions transactions = Transactions.builder()
                .transactionType("PACKAGE-ADD")
                .transactionDate(new Date())
                .description("Package with id: " + packageObj.getId() + " has been added")
                .build();
        transactionsRepository.save(transactions);

        // publish "package-added" to the kafka stream
        kafkaTemplate.send(PACKAGE_ADDED_TOPIC, packageObj);

        return packageObj;
    }

    @Override
    public Package editPackage(PackageDTO packageDTO) {
        Package packageObj = Package.builder()
                .id(packageDTO.id).name(packageDTO.name)
                .description(packageDTO.description).coins(packageDTO.coins)
                .build();
        repository.save(packageObj);
        Transactions transactions = Transactions.builder()
                .transactionType("PACKAGE-UPDATE")
                .transactionDate(new Date())
                .description("Package with id: " + packageDTO.id + " has been updated")
                .build();
        transactionsRepository.save(transactions);

        // publish "package-updated" to the kafka stream
        kafkaTemplate.send(PACKAGE_UPDATED_TOPIC, packageObj);

        return packageObj;
    }

    @Override
    public void deletePackage(String id) {
        repository.deleteById(id);
        // Update transactions
        Transactions transactions = Transactions.builder()
                .transactionType("PACKAGE-DELETED")
                .transactionDate(new Date())
                .description("Package with id: " + id + " has been deleted")
                .build();
        transactionsRepository.save(transactions);

        PackageDeletedEvent packageDeletedEvent = new PackageDeletedEvent(id);

        // publish "package-deleted" to the kafka stream
        kafkaTemplate.send(PACKAGE_DELETED_TOPIC, packageDeletedEvent);
    }
}
