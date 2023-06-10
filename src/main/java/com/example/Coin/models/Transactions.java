package com.example.Coin.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Transactions {
    @Id
    public Integer id;

    public Date transactionDate;

    public String description;

    public String transactionType;

    public long amount;

    public long userId;

}
