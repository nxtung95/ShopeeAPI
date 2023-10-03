package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.ItemData;
import lombok.Getter;

@Getter
public class UpdateItemResponse extends BaseResponse {
	public ItemData item;

	@SerializedName(value = "item_id")
	public long itemId;

	public String warning;

}