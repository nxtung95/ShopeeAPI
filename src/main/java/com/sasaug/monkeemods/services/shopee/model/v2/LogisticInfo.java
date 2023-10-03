package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogisticInfo {

	@SerializedName(value = "logistic_id")
	public long id;

	public boolean enabled;

	@SerializedName(value = "shipping_fee")
	public double shippingFee;

	@SerializedName(value = "size_id")
	public long sizeId;

	@SerializedName(value = "is_free")
	public boolean isFree;

}
