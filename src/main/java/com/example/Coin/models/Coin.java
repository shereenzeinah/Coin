package com.example.Coin.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coins")
@AllArgsConstructor
@Getter
@Setter
public class Coin {

    @Id
    private Long id;

    private Long userId;

    private Long balanceAvailable;
}
