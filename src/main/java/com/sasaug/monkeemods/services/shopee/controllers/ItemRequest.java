package com.sasaug.monkeemods.services.shopee.controllers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequest {
	Long itemId;
	Long modelId;
	double price;
	int quantity;
}
