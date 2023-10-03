package com.sasaug.monkeemods.services.shopee.controllers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddItemRequest {
	String categoryId;
	String name;
	String description;
	long sku;
	double price;
	int stock;
	double weight;
	int length;
	int width;
	int height;
	String brand;
	String images;
}
