package com.sasaug.monkeemods.services.shopee.controllers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
	private String cursor;
	private int pageSize;
	private List<String> orderIds;
}
