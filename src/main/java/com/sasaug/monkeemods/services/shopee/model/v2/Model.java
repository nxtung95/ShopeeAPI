package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Model {
	@SerializedName(value = "price_info")
	private List<PriceInfo> priceInfo;

	@SerializedName(value = "model_id")
	private Long modelId;

	@SerializedName(value = "tier_index")
	public List<Integer> tierIndex = new ArrayList<>();

	@SerializedName(value = "promotion_id")
	private int promotion_id;

	@SerializedName(value = "model_sku")
	public String modelSku;

	@SerializedName(value = "model_status")
	public String modelStatus;

	@SerializedName(value = "gtin_code")
	public String gtinCode;

	@SerializedName(value = "pre_order")
	private PreOrder preOrder;

	@SerializedName(value = "stock_info_v2")
	private StockInfoV2 stockInfoV2;

	@SerializedName(value = "original_price")
	public double originalPrice;

	@SerializedName(value = "seller_stock")
	public List<SellerStock> sellerStockList = new ArrayList<>();


}
