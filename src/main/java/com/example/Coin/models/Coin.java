package com.example.Coin.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coins")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coin {

    @Id
    private String id;

    private Long userId;

    private int balanceAvailable;
}
