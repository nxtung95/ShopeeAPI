package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteItemRequest extends BaseRequest {
	@SerializedName(value = "item_id")
	public long itemId;
}
