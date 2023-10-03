package com.sasaug.monkeemods.services.shopee.model.v2.submodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.sasaug.monkeemods.services.shopee.model.v2.ItemList;
import lombok.Getter;

@Getter
public class GetItemsListResponseModel {
	public List<ItemList> item = new ArrayList<>();

	@SerializedName("has_next_page")
	public boolean hasNextPage;

	@SerializedName("total_count")
	public int totalCount;

	@SerializedName("next_offset")
	public int nextOffset;
}
