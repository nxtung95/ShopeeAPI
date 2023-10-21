package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.enumeration.ShippingDocumentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShippingDocumentRequestModel {
	@SerializedName(value = "order_sn")
	public String orderId;

	@SerializedName(value = "package_number")
	private String trackingNumber;

	@SerializedName(value = "shipping_document_type")
	String shippingDocumentType;
}
