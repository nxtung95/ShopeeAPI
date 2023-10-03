package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirwayBillError {
	@SerializedName("ordersn")
	public String orderId;

	@SerializedName("error_code")
	public String errorCode;

	@SerializedName("error_description")
	public String errorDescription;

}
