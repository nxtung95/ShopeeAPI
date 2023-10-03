package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {
	public long timestamp = System.currentTimeMillis() / 1000L;

	@SerializedName(value = "shopid")
	public long shopId;
	@SerializedName(value = "partner_id")
	public long partnerId;

}
