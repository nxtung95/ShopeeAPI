package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemDimensionModel {

	@SerializedName("package_height")
	public int height;

	@SerializedName("package_width")
	public int width;

	@SerializedName("package_length")
	public int length;


}
