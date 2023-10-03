package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetItemsListRequest extends BaseRequest {

	@SerializedName(value = "pagination_offset")
	public int paginationOffset;

	@SerializedName(value = "pagination_entries_per_page")
	public int pageSize;

	@SerializedName(value = "need_deleted_item")
	public boolean needDeletedItem;


}
