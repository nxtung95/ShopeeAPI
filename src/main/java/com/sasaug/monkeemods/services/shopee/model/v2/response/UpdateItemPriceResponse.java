package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v1.response.BaseResponse;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.UpdatePriceItemResponseModel;
import lombok.Getter;

@Getter
public class UpdateItemPriceResponse extends BaseResponse {
	public UpdatePriceItemResponseModel response;
}
