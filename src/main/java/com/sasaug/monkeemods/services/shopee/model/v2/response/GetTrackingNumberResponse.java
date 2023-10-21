package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetItemBaseInfoResponseModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetTrackingNumberResponseModel;
import lombok.Getter;

@Getter
public class GetTrackingNumberResponse extends BaseResponse {
	GetTrackingNumberResponseModel response;
}
