package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetModelListResponseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetModelListResponse extends BaseResponse {
	GetModelListResponseModel response;
}
