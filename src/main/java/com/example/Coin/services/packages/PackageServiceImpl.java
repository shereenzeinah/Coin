package com.example.Coin.services.packages;

import com.example.Coin.models.Package;
import com.example.Coin.models.PackageDTO;
import com.example.Coin.models.Transactions;
import com.example.Coin.repository.PackageRepository;
import com.example.Coin.repository.TransactionsRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    PackageRepository repository;

    
    @Autowired
    TransactionsRepository transactionsRepository;

    @Override
    public List<Package> getAll() {
        return repository.findAll();
    }

    @Override
    public Package createPackage(PackageDTO packageDTO) {
        Package packageObj = new Package(packageDTO.name, packageDTO.description, packageDTO.coins);
        repository.save(packageObj);
        Transactions transactions = Transactions.builder()
                .transactionType("PACKAGE-ADD")
                .transactionDate(new Date())
                .description("Package with id: " + packageObj.getId() +" has been added")
                .build();
        transactionsRepository.save(transactions);
        
        return packageObj;
    }

    @Override
    public Package editPackage(PackageDTO packageDTO) {
        Package packageObj = new Package(packageDTO.id, packageDTO.name, packageDTO.description, packageDTO.coins);
        repository.save(packageObj);
        Transactions transactions = Transactions.builder()
                .transactionType("PACKAGE-UPDATE")
                .transactionDate(new Date())
                .description("Package with id: " + packageDTO.id +" has been updated")
                .build();
        transactionsRepository.save(transactions);
        return packageObj;
    }

    @Override
    public void deletePackage(String id) {
        repository.deleteById(id);
        //Update transactions
        Transactions transactions = Transactions.builder()
                .transactionType("PACKAGE-DELETED")
                .transactionDate(new Date())
                .description("Package with id: " +id +" has been deleted")
                .build();
        transactionsRepository.save(transactions);
    }
}
