package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPriceInfo {

	public String currency;

	@SerializedName(value = "original_price")
	public double originalPrice;

	@SerializedName(value = "current_price")
	public double currentPrice;

}
