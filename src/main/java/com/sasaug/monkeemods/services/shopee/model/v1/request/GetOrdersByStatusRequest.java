package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrdersByStatusRequest extends BaseRequest {

	@SerializedName(value = "order_status")
	public String orderStatus;

	@SerializedName(value = "pagination_offset")
	public int page;

	@SerializedName(value = "pagination_entries_per_page")
	public int pageSize;

}
