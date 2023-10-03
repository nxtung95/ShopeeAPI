package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.AddItemResponseModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetItemBaseInfoResponseModel;
import lombok.Getter;

@Getter
public class AddItemResponse extends BaseResponse {
	AddItemResponseModel response;
}
