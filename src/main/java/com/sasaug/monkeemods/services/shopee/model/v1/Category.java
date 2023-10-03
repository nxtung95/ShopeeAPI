package com.sasaug.monkeemods.services.shopee.model.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

	@SerializedName(value = "category_id")
	public long id;

	@SerializedName(value = "parent_id")
	public long parentId;

	@SerializedName(value = "category_name")
	public String name;

}
