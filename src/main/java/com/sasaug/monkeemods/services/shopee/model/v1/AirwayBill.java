package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirwayBill {
	@SerializedName("ordersn")
	public String orderId;

	@SerializedName("airway_bill")
	public String url;


}
