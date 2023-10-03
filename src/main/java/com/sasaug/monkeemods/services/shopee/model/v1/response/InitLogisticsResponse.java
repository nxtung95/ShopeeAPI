package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class InitLogisticsResponse extends BaseResponse {

	@SerializedName(value = "tracking_number")
	public String trackingNumber;

}
