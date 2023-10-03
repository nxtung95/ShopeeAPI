package com.sasaug.monkeemods.services.shopee.model.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

	@SerializedName(value = "category_id")
	public long categoryId;

	@SerializedName(value = "parent_category_id")
	public long parentCategoryId;

	@SerializedName(value = "original_category_name")
	public String originalCategoryName;

	@SerializedName("display_category_name")
	public String displayCategoryName;

	@SerializedName("has_children")
	public Boolean hasChildren;
}
