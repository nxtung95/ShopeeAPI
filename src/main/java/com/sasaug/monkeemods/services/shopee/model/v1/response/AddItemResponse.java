package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.ItemData;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AddItemResponse extends BaseResponse {
	public ItemData item;

	@SerializedName(value = "item_id")
	public long itemId;

	public String warning;

	@SerializedName(value = "fail_image")
	public List<String> failImage = new ArrayList<>();

}
