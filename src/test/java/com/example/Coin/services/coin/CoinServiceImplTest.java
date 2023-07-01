package com.example.Coin.services.coin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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

    @Test
    public void addCoins_ok() {
        Coin coin = new Coin(1L, 1L, 1L);
        doReturn(coin).when(mockCoinRepository.findByUserId(any()));
        target.addCoins(any(), any());
        verify(mockTransactionsRepository.save(any()));
        verify(mockCoinRepository.save(any()));
    }

    @Test
    public void getCoins_ok() {
        target.getCoins(any());
        verify(mockCoinRepository.findByUserId(any()));
    }
    
    @Test
    public void deductCoins_ok() {
        Coin coin = new Coin(1L, 1L, 1L);
        doReturn(coin).when(mockCoinRepository.findByUserId(any()));
        target.addCoins(any(), any());
        verify(mockTransactionsRepository.save(any()));
        verify(mockCoinRepository.save(any()));
    }

    @Test
    public void validateBalance_balanceAvailable_ok() {
        Coin coin = new Coin(1L, 1L, 500L);
        doReturn(coin).when(mockCoinRepository.findByUserId(any()));
        Boolean balanceAvailable = target.validateBalance(any(), 200L);
        assertThat(balanceAvailable).isEqualTo(true);
    }

    @Test
    public void validateBalance_balanceNotAvailable_ok() {
        Coin coin = new Coin(1L, 1L, 500L);
        doReturn(coin).when(mockCoinRepository.findByUserId(any()));
        Boolean balanceAvailable = target.validateBalance(any(), 700L);
        assertThat(balanceAvailable).isEqualTo(false);
    }
}
