package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemAttributeModel {

	@SerializedName("attribute_id")
	public long attributeId;

	@SerializedName("attribute_value_list")
	public List<AddItemAttributeValueModel> attributeValues = new ArrayList<>();

}
