package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemList {

	@SerializedName(value = "item_id")
	public long id;

	public String status;

	@SerializedName(value = "update_time")
	public long updateTime;

}
