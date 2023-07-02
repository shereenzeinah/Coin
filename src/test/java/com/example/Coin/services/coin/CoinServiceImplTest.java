package com.example.Coin.services.coin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.Coin.models.Coin;
import com.example.Coin.repository.CoinRepository;
import com.example.Coin.repository.TransactionsRepository;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class CoinServiceImplTest {

    @InjectMocks
    CoinServiceImpl target;

    @Mock
    CoinRepository mockCoinRepository;

    @Mock
    TransactionsRepository mockTransactionsRepository;

    @Mock
    KafkaTemplate kafkaTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addCoins_ok() {
        Coin coin = new Coin("id", 1L, 1);
        doReturn(coin).when(mockCoinRepository).findByUserId(any());
        target.addCoins(1L, 200);
        verify(mockTransactionsRepository).save(any());
        verify(mockCoinRepository).save(any());
    }

    @Test
    public void getCoins_ok() {
        Coin coin = new Coin();
        coin.setBalanceAvailable(100);
        doReturn(coin).when(mockCoinRepository).findByUserId(any());
        int response = target.getCoins(any());
        assertThat(response).isEqualTo(coin.getBalanceAvailable());
    }

    @Test
    public void deductCoins_ok() {
        Coin coin = new Coin("id", 1L, 1);
        doReturn(coin).when(mockCoinRepository).findByUserId(any());
        target.addCoins(1L, 100);
        verify(mockTransactionsRepository).save(any());
        verify(mockCoinRepository).save(any());
    }

    @Test
    public void validateBalance_balanceAvailable_ok() {
        Coin coin = new Coin("id", 1L, 500);
        doReturn(coin).when(mockCoinRepository).findByUserId(any());
        Boolean balanceAvailable = target.validateBalance(1L, 200);
        assertThat(balanceAvailable).isEqualTo(true);
    }

    @Test
    public void validateBalance_balanceNotAvailable_ok() {
        Coin coin = new Coin("id", 1L, 500);
        doReturn(coin).when(mockCoinRepository).findByUserId(any());
        Boolean balanceAvailable = target.validateBalance(1L, 700);
        assertThat(balanceAvailable).isEqualTo(false);
    }
}
