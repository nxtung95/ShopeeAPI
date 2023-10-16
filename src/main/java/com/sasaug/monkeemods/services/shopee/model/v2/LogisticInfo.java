package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogisticInfo {

	@SerializedName(value = "logistic_id")
	public long id;

	@SerializedName(value = "logistic_name")
	public String logisticName;

	public boolean enabled;

	@SerializedName(value = "size_id")
	public long sizeId;

	@SerializedName(value = "is_free")
	public boolean isFree;

	@SerializedName(value = "estimated_shipping_fee")
	public double estimatedShippingFee;

	@SerializedName(value = "shipping_fee")
	public double shippingFee;

}
