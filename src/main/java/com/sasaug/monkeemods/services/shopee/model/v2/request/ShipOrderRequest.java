package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Dropoff;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipOrderRequest extends BaseRequest {
	@SerializedName(value = "ordersn")
	public String orderId;

	@SerializedName(value = "dropoff")
	public Dropoff dropoff;
}
