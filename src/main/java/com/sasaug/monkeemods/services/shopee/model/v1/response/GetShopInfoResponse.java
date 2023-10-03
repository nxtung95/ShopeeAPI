package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class GetShopInfoResponse extends BaseResponse {

	@SerializedName(value = "shop_name")
	public String shopName;


}
