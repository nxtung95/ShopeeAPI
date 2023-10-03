package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetItemBaseInfoResponseModel;
import lombok.Getter;

@Getter
public class GetTrackingNumberResponse extends BaseResponse {
	GetItemBaseInfoResponseModel response;
}
