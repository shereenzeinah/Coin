package com.example.Coin.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Package {
	@Id
	private String id;

	private String name;
	private String description;
	private Integer coins;

}