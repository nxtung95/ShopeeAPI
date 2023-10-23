package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ShippingDocumentOrderRq;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DownloadShippingDocumentRequest extends BaseRequest {
	@SerializedName(value = "shipping_document_type")
	private String shippingDocumentType;

	@SerializedName(value = "order_list")
	private List<ShippingDocumentOrderRq> orderList;
}
