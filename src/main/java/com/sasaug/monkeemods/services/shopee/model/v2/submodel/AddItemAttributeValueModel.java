package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemAttributeValueModel {

	@SerializedName("value_id")
	public long valueId;

	@SerializedName("original_value_name")
	public String originalValueName;

}
