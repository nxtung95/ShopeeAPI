package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddImageResponseModel {
	@SerializedName(value = "image_info")
	public AddImageInfoResponseModel imageInfo;

}
