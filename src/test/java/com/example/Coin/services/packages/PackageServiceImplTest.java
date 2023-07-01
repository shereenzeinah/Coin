package com.example.Coin.services.packages;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.Coin.repository.PackageRepository;
import com.example.Coin.repository.TransactionsRepository;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class PackageServiceImplTest {

    @InjectMocks
    PackageServiceImpl target;

    @Mock
    PackageRepository mockPackageRepository;

    @Mock
    TransactionsRepository mockTransactionsRepository;

    @Test
    public void createPackage_ok() {
        target.createPackage(any());
        verify(mockTransactionsRepository.save(any()));
        verify(mockPackageRepository.save(any()));
    }

    @Test
    public void getAll_ok() {
        target.getAll();
        verify(mockPackageRepository.findAll());
    }

    @Test
    public void editPackage_ok() {
        target.editPackage(any());
        verify(mockTransactionsRepository.save(any()));
        verify(mockPackageRepository.save(any()));
    }

}
