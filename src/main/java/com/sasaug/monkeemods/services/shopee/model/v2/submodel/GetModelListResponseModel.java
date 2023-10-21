package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Model;
import com.sasaug.monkeemods.services.shopee.model.v2.TierVariation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetModelListResponseModel {
	@SerializedName("tier_variation")
	private List<TierVariation> tierVariation;

	@SerializedName("model")
	private List<Model> model;
}
