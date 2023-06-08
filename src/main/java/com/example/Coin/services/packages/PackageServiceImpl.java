package com.example.Coin.services.packages;

import com.example.Coin.models.Package;
import com.example.Coin.models.PackageDTO;

import com.example.Coin.repository.PackageRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    PackageRepository repository;

    @Override
    public List<Package> getAll() {
        return repository.findAll();
    }

    @Override
    public Package createPackage(PackageDTO packageDTO) {
        Package packageObj = new Package(packageDTO.name, packageDTO.description, packageDTO.coins);
        repository.save(packageObj);

        return packageObj;
    }

    @Override
    public Package editPackage(PackageDTO packageDTO) {
        Package packageObj = new Package(packageDTO.id, packageDTO.name, packageDTO.description, packageDTO.coins);
        repository.save(packageObj);

        return packageObj;
    }

    @Override
    public void deletePackage(String id) {
        repository.deleteById(id);
    }
}
