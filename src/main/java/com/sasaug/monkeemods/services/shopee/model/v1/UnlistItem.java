package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UnlistItem {

	@SerializedName(value = "item_id")
	public long itemId;

	public boolean unlist = true;

	@SerializedName(value = "error_descritpion")
	public String errorDescription;

}
