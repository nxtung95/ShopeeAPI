package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Order;
import lombok.Getter;

@Getter
public class GetOrderDetailResponseModel {
	@SerializedName("order_list")
	public List<Order> orders = new ArrayList<>();
}
