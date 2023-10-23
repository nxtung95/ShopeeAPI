package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.WarningGetShippingDocument;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetShippingDocumentResonseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetShippingDocumentResponse {
	@SerializedName(value = "request_id")
	public String requestId;

	public String error;
	public String message;
	private List<WarningGetShippingDocument> warning;
	GetShippingDocumentResonseModel response;
}
