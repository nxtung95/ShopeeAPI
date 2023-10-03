package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v1.ItemData;
import com.sasaug.monkeemods.services.shopee.model.v1.response.BaseResponse;
import lombok.Getter;

@Getter
public class UpdateItemPriceResponse extends BaseResponse {
	public ItemData item;
}
