package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDocumentOrderRq {
	@SerializedName(value = "order_sn")
	private String orderSn;
	@SerializedName(value = "package_number")
	private String packageNumber;
	@SerializedName(value = "shipping_document_type")
	private String shippingDocumentType; //The type of shipping document. Available values: NORMAL_AIR_WAYBILL, THERMAL_AIR_WAYBILL, NORMAL_JOB_AIR_WAYBILL, THERMAL_JOB_AIR_WAYBILL
}
