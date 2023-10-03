package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemVariation {

	@SerializedName(value = "variation_sku")
	public String sku;

	public String name;

	public double price;

	public int stock;

}
