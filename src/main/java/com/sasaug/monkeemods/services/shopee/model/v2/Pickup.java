package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pickup {
	@SerializedName("address_id")
	private long addressId;

	@SerializedName("pickup_time_id")
	private long pickupTimeId;

	@SerializedName("tracking_number")
	private String trackingNumber;
}
