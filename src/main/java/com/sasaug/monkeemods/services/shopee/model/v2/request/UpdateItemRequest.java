package com.sasaug.monkeemods.services.shopee.model.v2.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.BrandInfo;
import com.sasaug.monkeemods.services.shopee.model.v2.LogisticInfo;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemAttributeModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemDimensionModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemImageModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemSellerStockModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long id;

	public String description;

	@SerializedName(value = "item_name")
	public String name;

	@SerializedName(value = "category_id")
	public long categoryId;

	public double weight;

	@SerializedName(value = "logistic_info")
	public List<LogisticInfo> logistics = new ArrayList<>();

	@SerializedName(value = "attribute_list")
	public List<AddItemAttributeModel> attributes = new ArrayList<>();

	@SerializedName(value = "item_sku")
	public String itemSku;

	@SerializedName(value = "item_status")
	public String status;

	public BrandInfo brand;

	@SerializedName(value = "item_dangerous")
	public int itemDangerous = 0;

	public String condition;

}
