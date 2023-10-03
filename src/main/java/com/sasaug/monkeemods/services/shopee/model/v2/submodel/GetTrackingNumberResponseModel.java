package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemBase;
import lombok.Getter;

@Getter
public class GetTrackingNumberResponseModel {
	@SerializedName("tracking_number")
	public String trackingNumber;
}
