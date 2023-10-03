package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest {
	public long timestamp = System.currentTimeMillis() / 1000L;

	@SerializedName(value = "shop_id")
	public long shopId;
	@SerializedName(value = "partner_id")
	public long partnerId;

}
