package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShippingDocumentResultResponseModel {
	@SerializedName(value = "order_sn")
	public String orderId;

	@SerializedName(value = "order_list")
	public String orderList;
}
