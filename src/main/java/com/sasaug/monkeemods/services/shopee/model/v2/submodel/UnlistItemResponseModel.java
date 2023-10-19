package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.FailureUnlistItem;
import com.sasaug.monkeemods.services.shopee.model.v2.SuccessUnlistItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UnlistItemResponseModel {
	@SerializedName(value = "failure_list")
	private List<FailureUnlistItem> failureUnlistItem;
	@SerializedName(value = "success_list")
	private List<SuccessUnlistItem> successUnlistItem;
}
