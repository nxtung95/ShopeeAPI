package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemBase {

	@SerializedName(value = "item_id")
	public long id;

	@SerializedName(value = "category_id")
	public long categoryId;

	@SerializedName("item_name")
	public String name;

	public String description;

	@SerializedName(value = "item_sku")
	public String itemSku;

	@SerializedName(value = "create_time")
	public long createTime;

	@SerializedName(value = "update_time")
	public long updateTime;

	@SerializedName(value = "price_info")
	public List<ItemPriceInfo> priceInfo;

	@SerializedName(value = "stock_info_v2")
	public ItemStockInfo stockInfo;

	public String weight;

	@SerializedName("has_model")
	public boolean hasModel;


}
