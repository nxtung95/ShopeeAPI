package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetCategoryResponseModel {
	@SerializedName("category_list")
	public List<Category> categoryList = new ArrayList<>();
}
