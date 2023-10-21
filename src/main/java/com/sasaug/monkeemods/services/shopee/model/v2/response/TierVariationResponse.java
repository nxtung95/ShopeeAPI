package com.sasaug.monkeemods.services.shopee.model.v2.response;

import com.sasaug.monkeemods.services.shopee.model.v1.response.BaseResponse;
import com.sasaug.monkeemods.services.shopee.model.v2.submodel.TierVariationModel;
import lombok.Getter;

@Getter
public class TierVariationResponse extends BaseResponse {

	public TierVariationModel response;
}
