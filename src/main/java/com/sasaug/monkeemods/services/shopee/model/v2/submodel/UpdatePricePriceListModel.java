package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePricePriceListModel {

	@SerializedName("model_id")
	public long id;

	@SerializedName("original_price")
	public double price;

}
