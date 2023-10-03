package com.sasaug.monkeemods.services.shopee.model.v2.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.BrandInfo;
import com.sasaug.monkeemods.services.shopee.model.v2.LogisticInfo;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemAttributeModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemDimensionModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemImageModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.UpdatePricePriceListModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemPriceRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long id;

	@SerializedName(value = "price_list")
	public List<UpdatePricePriceListModel> priceList = new ArrayList<>();

}
