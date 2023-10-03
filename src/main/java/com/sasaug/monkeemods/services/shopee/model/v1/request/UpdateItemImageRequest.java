package com.sasaug.monkeemods.services.shopee.model.v1.request;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateItemImageRequest extends BaseRequest {

	@SerializedName(value = "item_id")
	public long itemId;

	public List<String> images = new ArrayList<>();
}
