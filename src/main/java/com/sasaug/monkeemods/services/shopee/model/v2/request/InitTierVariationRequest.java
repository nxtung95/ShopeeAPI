package com.sasaug.monkeemods.services.shopee.model.v2.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Model;
import com.sasaug.monkeemods.services.shopee.model.v2.TierVariation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitTierVariationRequest extends BaseRequest {
	@SerializedName(value = "tier_variation")
	public List<TierVariation> tierVariationList = new ArrayList<>();

	@SerializedName(value = "model")
	public List<Model> modelList = new ArrayList<>();

}
