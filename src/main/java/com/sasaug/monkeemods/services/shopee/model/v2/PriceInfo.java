package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceInfo {
	private String currency;

	@SerializedName("current_price")
	private double currentPrice;

	@SerializedName("origin_price")
	private double originalPrice;

	@SerializedName("inflated_price_of_original_price")
	private double inflatedPriceOfOriginalPrice;

	@SerializedName("inflated_price_of_current_price")
	private double inflatedPriceOfCurrentPrice;

	@SerializedName("sip_item_price")
	private double sipItemPrice;

	@SerializedName("sip_item_price_source")
	private String sipItemPriceSource;

	@SerializedName("sip_item_price_currency")
	private String sipItemPriceCurrency;
}
