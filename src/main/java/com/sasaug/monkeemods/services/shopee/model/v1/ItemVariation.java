package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVariation {

	@SerializedName(value = "variation_id")
	public long id;

	@SerializedName(value = "variation_sku")
	public String sku;

	public String name;

	public double price;

	public int stock;

	public String status;

	@SerializedName(value = "create_time")
	public long createTime;

	@SerializedName(value = "update_time")
	public long updateTime;

	@SerializedName(value = "original_price")
	public double originalPrice;

	@SerializedName(value = "discount_id")
	public long discountId;

	@SerializedName(value = "reserved_stock")
	public int reservedStock;

	@SerializedName(value = "inflated_price")
	public double inflatedPrice;

	@SerializedName(value = "inflated_original_price")
	public double inflatedOriginalPrice;

	@SerializedName(value = "sip_item_price")
	public double sipItemPrice;

	@SerializedName(value = "price_source")
	public String priceSource;


}
