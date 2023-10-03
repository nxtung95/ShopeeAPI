package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetItemDetailRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long itemId;

}
