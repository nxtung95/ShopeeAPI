package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.AddItemAttribute;
import com.sasaug.monkeemods.services.shopee.model.v1.ItemVariation;
import com.sasaug.monkeemods.services.shopee.model.v1.Logistic;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateItemRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long itemId;

	@SerializedName(value = "category_id")
	public long categoryId;

	public String name;

	public String description;

	@SerializedName(value = "item_sku")
	public String itemSku;

	@SerializedName(value = "item_status")
	public String itemStatus;

	public List<ItemVariation> variations = new ArrayList<>();
	public List<AddItemAttribute> attributes = new ArrayList<>();
	public List<Logistic> logistics = new ArrayList<>();

	public double weight;

	public String condition;

	@SerializedName(value = "is_pre_order")
	public boolean isPreOrder;

	@SerializedName(value = "item_dangerous")
	public int itemDangerous = 0;
}
