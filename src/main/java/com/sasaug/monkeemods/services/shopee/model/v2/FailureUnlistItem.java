package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailureUnlistItem {

	@SerializedName(value = "item_id")
	public long itemId;

	@SerializedName(value = "failed_reason")
	public String failedReason;

}
