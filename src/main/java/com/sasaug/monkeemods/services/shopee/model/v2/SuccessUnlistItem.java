package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessUnlistItem {

	@SerializedName(value = "item_id")
	public long itemId;

	public boolean unlist;

}
