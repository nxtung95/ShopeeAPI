package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddImageResponseModel;
import lombok.Getter;

@Getter
public class UpdateStockResponse extends BaseResponse {
	AddImageResponseModel response;
}
