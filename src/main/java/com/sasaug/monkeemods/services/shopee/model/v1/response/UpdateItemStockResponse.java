package com.sasaug.monkeemods.services.shopee.model.v1.response;

import com.sasaug.monkeemods.services.shopee.model.v1.ItemData;
import lombok.Getter;

@Getter
public class UpdateItemStockResponse extends BaseResponse {
	public ItemData item;
}
