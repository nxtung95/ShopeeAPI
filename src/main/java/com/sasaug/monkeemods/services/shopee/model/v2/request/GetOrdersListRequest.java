package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrdersListRequest extends BaseRequest {
	@SerializedName(value = "time_range_field")
	public String timeRangeField;

	@SerializedName(value = "time_from")
	public long timeFrom;

	@SerializedName(value = "time_to")
	public long timeTo;

	@SerializedName(value = "page_size")
	public int pageSize;

	public String cursor;

	@SerializedName(value = "order_status")
	public String status;

}
