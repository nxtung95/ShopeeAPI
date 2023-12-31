package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemResponseImageModel {

	@SerializedName("image_id_list")
	public List<String> imageIds = new ArrayList<>();

	@SerializedName("image_url_list")
	public List<String> imageUrls = new ArrayList<>();

}
