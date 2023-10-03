package com.sasaug.monkeemods.services.shopee.model.v2;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {

	@SerializedName(value = "tier_index")
	public List<Integer> tierIndex = new ArrayList<>();

	@SerializedName(value = "original_price")
	public double originalPrice;

	@SerializedName(value = "model_sku")
	public String modelSku;

	@SerializedName(value = "seller_stock")
	public List<SellerStock> sellerStockList = new ArrayList<>();

	@SerializedName(value = "gtin_code")
	public String gtinCode;


}
