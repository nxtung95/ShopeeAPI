package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemStockSellerShopeeInfo {
	@SerializedName(value = "location_id")
	public String locationId;

	public int stock;

}
