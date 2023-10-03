package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePriceResponseSuccessModel {
	@SerializedName(value = "model_id")
	public long modelId;

	@SerializedName(value = "original_price")
	public double price;
}
