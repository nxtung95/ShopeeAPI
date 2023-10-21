package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModelResponseModel {
	private List<Model> model;
	@SerializedName("item_id")
	private Long itemId;
	@SerializedName("model_id")
	private Long modelId;
}
