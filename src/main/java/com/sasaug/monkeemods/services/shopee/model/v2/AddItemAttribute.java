package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemAttribute {

	@SerializedName(value = "attributes_id")
	public long id;

	@SerializedName(value = "value")
	public String value;

}
