package com.sasaug.monkeemods.services.shopee.model.v2;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemStockInfo {

	@SerializedName(value = "summary_info")
	public ItemStockSummaryInfo summaryInfo;

	@SerializedName(value = "seller_stock")
	public List<ItemStockSellerShopeeInfo> sellerStock;

	@SerializedName(value = "shopee_stock")
	public List<ItemStockSellerShopeeInfo> shopeeStock;

}
