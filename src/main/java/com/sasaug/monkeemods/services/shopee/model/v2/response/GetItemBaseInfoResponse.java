package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetItemBaseInfoResponseModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetItemsListResponseModel;
import lombok.Getter;

@Getter
public class GetItemBaseInfoResponse extends BaseResponse {
	GetItemBaseInfoResponseModel response;
}
