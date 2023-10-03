package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnlistItem {

	@SerializedName(value = "item_id")
	public long itemId;

	public boolean unlist = true;

	@SerializedName(value = "error_descritpion")
	public String errorDescription;

}
