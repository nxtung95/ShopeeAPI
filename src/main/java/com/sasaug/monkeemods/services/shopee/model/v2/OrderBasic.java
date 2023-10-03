package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBasic {
	@SerializedName(value = "ordersn")
	public String id;

	@SerializedName(value = "order_status")
	public String status;
}
