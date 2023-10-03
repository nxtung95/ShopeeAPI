package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePriceResponseModel {
	@SerializedName(value = "failure_list")
	public List<UpdatePriceResponseFailureModel> failureList = new ArrayList<>();

	@SerializedName(value = "success_list")
	public List<UpdatePriceResponseSuccessModel> successList = new ArrayList<>();


}
