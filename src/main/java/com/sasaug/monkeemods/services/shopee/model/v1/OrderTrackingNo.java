package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderTrackingNo {
	@SerializedName(value = "ordersn")
	public String id;

	@SerializedName(value = "trackingno")
	public String trackingNo;
}
