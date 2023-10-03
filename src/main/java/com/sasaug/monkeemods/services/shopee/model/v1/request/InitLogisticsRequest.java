package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.Dropoff;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitLogisticsRequest extends BaseRequest {
	@SerializedName(value = "ordersn")
	public String orderId;

	@SerializedName(value = "dropoff")
	public Dropoff dropoff;
}
