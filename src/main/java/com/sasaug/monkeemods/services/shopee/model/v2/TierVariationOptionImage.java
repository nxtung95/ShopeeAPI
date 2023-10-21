package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TierVariationOptionImage {

	@SerializedName(value = "image_id")
	public String id;

	@SerializedName(value = "image_url")
	public String imageUrl;

}
