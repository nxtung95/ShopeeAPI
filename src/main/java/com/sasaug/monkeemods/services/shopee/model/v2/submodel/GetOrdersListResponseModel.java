package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemList;
import com.sasaug.monkeemods.services.shopee.model.v2.OrderBasic;
import lombok.Getter;

@Getter
public class GetOrdersListResponseModel {
	@SerializedName("order_list")
	public List<OrderBasic> orders = new ArrayList<>();

	@SerializedName("more")
	public boolean more;

	@SerializedName("next_cursor")
	public String nextCursor;
}
