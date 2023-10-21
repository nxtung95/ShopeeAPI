package com.sasaug.monkeemods.services.shopee.model.v2.request;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModelRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long id;

	@SerializedName(value = "model_id")
	public long modelId;

	@SerializedName(value = "model_list")
	private List<Model> modelList;

	@SerializedName(value = "model")
	private List<Model> updateModelList;
}
