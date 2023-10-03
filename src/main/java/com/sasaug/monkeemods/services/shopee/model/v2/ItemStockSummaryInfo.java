package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemStockSummaryInfo {
	@SerializedName(value = "total_reserved_stock")
	public int totalReservedStock;

	@SerializedName(value = "total_available_stock")
	public int totalAvailableStock;

}
