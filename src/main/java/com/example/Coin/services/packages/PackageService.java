package com.example.Coin.services.packages;

import java.util.List;
import com.example.Coin.models.Package;
import com.example.Coin.models.PackageDTO;

public interface PackageService {

    List<Package> getAll();

    Package createPackage(PackageDTO packageDTO);

    Package editPackage(PackageDTO packageDTO);

    void deletePackage(String id);
}
