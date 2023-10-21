package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v1.response.BaseResponse;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetOrderDetailResponseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderDetailsResponse extends BaseResponse {

	private GetOrderDetailResponseModel response;
}
