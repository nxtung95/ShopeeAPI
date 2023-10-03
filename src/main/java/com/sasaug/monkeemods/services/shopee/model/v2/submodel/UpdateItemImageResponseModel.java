package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemPriceInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemImageResponseModel {
	@SerializedName(value = "image_id_list")
	public List<String> imageIdList;

	@SerializedName(value = "image_url_list")
	public List<String> imageUrlList;


}
