package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShippingDocumentResponseModel {
	@SerializedName(value = "result_list")
	public List<CreateShippingDocumentResultResponseModel> results = new ArrayList<>();
}
