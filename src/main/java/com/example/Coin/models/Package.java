package com.example.Coin.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
@Getter
@Setter
public class Package {
	@Id
	private String id;

	private String name;
	private String description;
	private Integer coins;

	public Package() {

	}

	public Package(String name, String description, Integer coins) {
		this.name = name;
		this.description = description;
		this.coins = coins;
	}

	public Package(String id, String name, String description, Integer coins) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.coins = coins;
	}

	@Override
	public String toString() {
		return "Package { "
				+ "id= " + id
				+ ", name= " + name
				+ ", description= " + description
				+ ", coins= " + coins
				+ "}";
	}
}