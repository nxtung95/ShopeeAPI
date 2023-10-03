package com.sasaug.monkeemods.services.shopee.model.v2.submodel;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddImageInfoUrlListResponseModel {
	@SerializedName(value = "image_url_region")
	public String region;

	@SerializedName(value = "image_url")
	public String url;
}
