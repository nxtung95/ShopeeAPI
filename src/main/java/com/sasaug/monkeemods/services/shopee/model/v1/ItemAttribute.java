package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAttribute {

	@SerializedName(value = "attribute_id")
	public long id;

	@SerializedName(value = "attribute_name")
	public String name;

	@SerializedName(value = "is_mandatory")
	public boolean isMandatory;

	@SerializedName(value = "attribute_type")
	public String type;

	@SerializedName(value = "attribute_value")
	public String value;

}
