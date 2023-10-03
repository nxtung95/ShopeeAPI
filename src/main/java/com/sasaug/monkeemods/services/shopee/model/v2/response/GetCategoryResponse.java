package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetCategoryResponseModel;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.GetItemBaseInfoResponseModel;
import lombok.Getter;

@Getter
public class GetCategoryResponse extends BaseResponse {
	GetCategoryResponseModel response;
}
