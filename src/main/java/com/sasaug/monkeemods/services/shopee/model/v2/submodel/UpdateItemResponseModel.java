package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemResponseModel {
	@SerializedName(value = "item_id")
	public long id;

	@SerializedName(value = "category_id")
	public long categoryId;

	@SerializedName("item_name")
	public String name;

	public String description;

	@SerializedName(value = "item_sku")
	public String itemSku;

	public int weight;

	public AddItemResponseImageModel images;

	@SerializedName(value = "price_info")
	public ItemPriceInfo priceInfo;

	private Dimension dimension;

	@SerializedName("logistic_info")
	private List<LogisticInfo> logisticInfoList;

	@SerializedName("pre_order")
	private PreOrder preOrder;

	private String condition;

	@SerializedName("item_status")
	private String itemStatus;

	private AddBrandInfo brand;

	@SerializedName("description_type")
	private String descriptionType;

	@SerializedName("seller_stock")
	private List<SellerStock> sellerStocks;

}
