package com.example.Coin.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
@AllArgsConstructor
@Getter
@Setter
public class Transactions {
}
