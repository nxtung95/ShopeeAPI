package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v2.submodel.ModelResponseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelResponse extends BaseResponse {
	public ModelResponseModel response;
}
