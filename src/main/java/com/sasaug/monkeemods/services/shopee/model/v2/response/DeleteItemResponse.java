package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.response.BaseResponse;
import lombok.Getter;

@Getter
public class DeleteItemResponse extends BaseResponse {
	@SerializedName(value = "item_id")
	public long itemId;

}
