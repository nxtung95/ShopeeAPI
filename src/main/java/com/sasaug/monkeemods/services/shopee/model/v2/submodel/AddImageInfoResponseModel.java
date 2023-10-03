package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemPriceInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddImageInfoResponseModel {
	@SerializedName(value = "image_id")
	public String id;

	@SerializedName(value = "image_url_list")
	public List<AddImageInfoUrlListResponseModel> imageUrlList = new ArrayList<>();

}
