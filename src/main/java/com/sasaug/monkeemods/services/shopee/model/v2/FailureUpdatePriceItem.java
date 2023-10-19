package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailureUpdatePriceItem {

	@SerializedName(value = "model_id")
	public long modelId;

	@SerializedName(value = "failed_reason")
	public String failedReason;

}
