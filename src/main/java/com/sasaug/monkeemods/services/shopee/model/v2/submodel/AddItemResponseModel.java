package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemPriceInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemResponseModel {
	@SerializedName(value = "item_id")
	public long id;

	@SerializedName(value = "category_id")
	public long categoryId;

	@SerializedName("item_name")
	public String name;

	public String description;

	@SerializedName(value = "item_sku")
	public String itemSku;

	public String weight;

	public List<AddItemResponseImageModel> images;

	@SerializedName(value = "item_status")
	public String status;

	@SerializedName(value = "price_info")
	public List<ItemPriceInfo> priceInfo;

}
