package com.example.Coin.api.packages;

import com.example.Coin.models.Package;
import com.example.Coin.models.PackageDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Coin.services.packages.PackageService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/package")
public class PackagesApi {

    @Autowired
    private PackageService packageService;

    @GetMapping(path = "/all", produces = "application/json")
    public List<Package> getAll() {
        return packageService.getAll();
    }

    @PostMapping(path = "/add", produces = "application/json", consumes = "application/json")
    public Package createPackage(@RequestBody PackageDTO packageDTO) {

        Package packageObj = packageService.createPackage(packageDTO);

        return packageObj;
    }

    @PostMapping(path = "/edit", produces = "application/json", consumes = "application/json")
    public Package editPackage(@RequestBody PackageDTO packageDTO) {

        Package packageObj = packageService.editPackage(packageDTO);

        return packageObj;
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePackage(@PathVariable String id) {

        packageService.deletePackage(id);
    }
}
