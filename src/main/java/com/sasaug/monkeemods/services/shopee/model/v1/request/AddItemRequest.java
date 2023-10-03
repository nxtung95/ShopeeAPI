package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemAttribute;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemVariation;
import com.sasaug.monkeemods.services.shopee.model.v1.ItemImage;
import com.sasaug.monkeemods.services.shopee.model.v1.Logistic;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddItemRequest extends BaseRequest {

	@SerializedName(value = "category_id")
	public long categoryId;

	public String name;

	public String description;

	public double price;

	public int stock;

	@SerializedName(value = "item_sku")
	public String itemSku;

	public List<AddItemVariation> variations = new ArrayList<>();
	public List<ItemImage> images = new ArrayList<>();
	public List<AddItemAttribute> attributes = new ArrayList<>();
	public List<Logistic> logistics = new ArrayList<>();

	@SerializedName(value = "package_length")
	public int packageLength;

	@SerializedName(value = "package_width")
	public int packageWidth;

	@SerializedName(value = "package_height")
	public int packageHeight;

	public double weight;

	public String condition;
	public String status;

	@SerializedName(value = "is_pre_order")
	public boolean isPreOrder;

	@SerializedName(value = "item_dangerous")
	public int itemDangerous = 0;
}
