package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockInfoV2 {
	@SerializedName("summary_info")
	Object summaryInfo;

	@SerializedName("seller_stock")
	List<SellerStock> sellerStock;

	@SerializedName("shopee_stock")
	List<SellerStock> shopeeStock;
}
