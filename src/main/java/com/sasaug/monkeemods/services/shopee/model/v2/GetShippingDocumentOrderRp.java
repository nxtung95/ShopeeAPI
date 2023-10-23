package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetShippingDocumentOrderRp {
	@SerializedName(value = "order_sn")
	private String orderSn;
	@SerializedName(value = "package_number")
	private String packageNumber;
	@SerializedName(value = "status")
	private String status;
	@SerializedName(value = "fail_error")
	private String failError;
	@SerializedName(value = "fail_message")
	private String failMessage;
}
