package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.GetShippingDocumentOrderRp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetShippingDocumentResonseModel {
	@SerializedName(value = "result_list")
	private List<GetShippingDocumentOrderRp> resultList;
}
