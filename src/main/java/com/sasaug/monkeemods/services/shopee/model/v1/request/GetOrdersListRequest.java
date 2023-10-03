package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrdersListRequest extends BaseRequest {
	@SerializedName(value = "pagination_offset")
	public int paginationOffset;

	@SerializedName(value = "pagination_entries_per_page")
	public int pageSize;

	@SerializedName(value = "create_time_from")
	public long createTimeFrom;

	@SerializedName(value = "create_time_to")
	public long createTimeTo;

	@SerializedName(value = "update_time_from")
	public long updateTimeFrom;

	@SerializedName(value = "update_time_to")
	public long updateTimeTo;


}
