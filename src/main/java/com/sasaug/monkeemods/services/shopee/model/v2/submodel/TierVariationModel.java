package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Model;
import com.sasaug.monkeemods.services.shopee.model.v2.TierVariation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TierVariationModel {

	@SerializedName("item_id")
	public long id;

	@SerializedName(value = "tier_variation")
	public List<TierVariation> tierVariationList = new ArrayList<>();

	@SerializedName(value = "model")
	public List<Model> modelList = new ArrayList<>();

}
