package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.FailureUnlistItem;
import com.sasaug.monkeemods.services.shopee.model.v2.FailureUpdatePriceItem;
import com.sasaug.monkeemods.services.shopee.model.v2.SuccessUnlistItem;
import com.sasaug.monkeemods.services.shopee.model.v2.SuccessUpdatePriceItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdatePriceItemResponseModel {
	@SerializedName(value = "failure_list")
	private List<FailureUpdatePriceItem> failureUpdatePriceItems;
	@SerializedName(value = "success_list")
	private List<SuccessUpdatePriceItem> successUpdatePriceItems;
}
