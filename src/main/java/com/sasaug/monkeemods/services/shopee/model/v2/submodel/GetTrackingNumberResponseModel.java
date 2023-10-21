package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class GetTrackingNumberResponseModel {
	@SerializedName("tracking_number")
	public String trackingNumber;

	@SerializedName("plp_number")
	public String plpNumber;

	@SerializedName("first_mile_tracking_number")
	public String firstMileTrackingNumber;

	@SerializedName("last_mile_tracking_number")
	public String lastMileTrackingNumber;

	@SerializedName("hint")
	public String hint;
}
