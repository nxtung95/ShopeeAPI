package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v1.UnlistItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UnlistItemRequest extends BaseRequest {

	@SerializedName(value = "item_list")
	public List<UnlistItem> items = new ArrayList<>();
}
