package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusUpdatePush extends Push {
	@SerializedName("shop_id")
	public long shopId;

	@SerializedName("data")
	public OrderBasic order;
}
