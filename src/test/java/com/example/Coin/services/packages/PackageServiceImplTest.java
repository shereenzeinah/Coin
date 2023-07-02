package com.example.Coin.services.packages;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.Coin.models.PackageDTO;
import com.example.Coin.repository.PackageRepository;
import com.example.Coin.repository.TransactionsRepository;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class PackageServiceImplTest {

    @Mock
    PackageRepository mockPackageRepository;

    @Mock
    TransactionsRepository mockTransactionsRepository;

    @Mock
    KafkaTemplate kafkaTemplate;

    @InjectMocks
    PackageServiceImpl target;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPackage_ok() {
        PackageDTO dto = new PackageDTO();
        dto.setCoins(100);
        dto.setDescription("description");
        dto.setId("id");
        target.createPackage(dto);
        verify(mockTransactionsRepository).save(any());
        verify(mockPackageRepository).save(any());
    }

    @Test
    public void getAll_ok() {
        target.getAll();
        verify(mockPackageRepository).findAll();
    }

    @Test
    public void editPackage_ok() {
        PackageDTO dto = new PackageDTO();
        dto.setCoins(100);
        dto.setDescription("description");
        dto.setId("id");
        target.editPackage(dto);
        verify(mockTransactionsRepository).save(any());
        verify(mockPackageRepository).save(any());
    }

}
