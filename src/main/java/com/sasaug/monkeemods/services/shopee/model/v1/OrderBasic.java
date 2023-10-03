package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderBasic {
	@SerializedName(value = "ordersn")
	public String id;

	@SerializedName(value = "order_status")
	public String status;

	@SerializedName(value = "update_time")
	public long updateTime;
}
