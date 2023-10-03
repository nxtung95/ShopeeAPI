package com.sasaug.monkeemods.services.shopee.model.v2.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.UnlistItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnlistItemRequest extends BaseRequest {

	@SerializedName(value = "item_list")
	public List<UnlistItem> items = new ArrayList<>();
}
