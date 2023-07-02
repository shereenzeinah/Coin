package com.example.Coin.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PackageDTO {

	public String id;
	public String name;
	public String description;
	public Integer coins;
}